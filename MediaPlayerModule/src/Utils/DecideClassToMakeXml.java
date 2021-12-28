package Utils;

import XmlClassType.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DecideClassToMakeXml {
    public static void decideClassToAccept(String type,int result){
        String objectXml;
        switch (type){
            case "REGIST_ID":
                // 回传给客户端它的Id,用于用户自己记住Id
                ClickOneUserOrSongOrSinger registId=new ClickOneUserOrSongOrSinger(RequestEnum.REGIST_A_USER,result);
                objectXml=XStreamUtil.objectToXml(registId);
                System.out.println(objectXml);
                ServerSocketUtils.Task.sendToClientXml(objectXml);
                break;
            case "SONGLIST_ID":
                ClickOneUserOrSongOrSinger songlistId=new ClickOneUserOrSongOrSinger(RequestEnum.CREATE_A_SONGLIST,result);
                objectXml=XStreamUtil.objectToXml(songlistId);
                ServerSocketUtils.Task.sendToClientXml(objectXml);
                break;
        }
    }

    public static void decideClassToAccept(String type, ResultSet resultSet){
        switch (type){
            case "CLICK_A_USER":
                UserInfo userInfo=new UserInfo();
                String[] res=new String[10];
                try{
                    while(resultSet.next()){
                        res[0]="CLICK_A_USER";
                        for(int i=1;i<=9;i++){
                            res[i]=resultSet.getString(i);
                        }
                        userInfo.setAll(res);
                        String objectXml=XStreamUtil.objectToXml(userInfo);
                        ServerSocketUtils.Task.sendToClientXml(objectXml);
                        //System.out.println(objectXml);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "CLICK_A_SONGLIST": //done:完成,SQL语句还不是很完善,点击歌单,显示歌曲名,歌手名,专辑名和歌曲时长
            case "CLICK_AN_ALBUM": //todo: 可能后续点开专辑和点开普通歌单的UI显示要不一样,但是暂时这样设置成一样,需求就够了
                //SelectSongsInSonglist selectSongsInSonglist=new SelectSongsInSonglist();
                String[] res2=new String[6];
                List<SongsWithNames> songsWithNames =new ArrayList<SongsWithNames>();
                String[] resForSong=new String[11];
                String kind="CLICK_A_SONGLIST";
                try{
                    if(resultSet.next()){
                        resultSet.beforeFirst();
                        while(resultSet.next()){
                            //String
                            res2[0]=kind;
                            for(int i=1;i<=3;i++){
                                res2[i]=resultSet.getString(i);
                                System.out.println(res2[i]);
                            }
                            Songs asong=new Songs();
                            resForSong[0]="CLICK_A_SONGLIST";
                            for(int i=1;i<=10;i++){ // 这里将一首song的属性全部赋值完毕
                                resForSong[i]=resultSet.getString(i+3);
                                System.out.println(resForSong[i]);
                            }
                            asong.setAll(resForSong);
                            songsWithNames.add(new SongsWithNames(kind,asong,asong.getSong_name(),res2[2],res2[3]));
                            //songs.add(asong);
                        }
                        if(songsWithNames!=null){
                            SelectSongsInSonglist selectSongsInSonglist=new SelectSongsInSonglist(kind,res2[1],songsWithNames);
                            //selectSongsInSonglist.setSongs(songsWithNames); // 一定记得要把这个歌单加进去
                            String objectXml=XStreamUtil.objectToXml(selectSongsInSonglist);
                            ServerSocketUtils.Task.sendToClientXml(objectXml);
                        }
                    }

                    //System.out.println(objectXml);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "CLICK_A_SONG":
                Songs asong=new Songs();
                String[] res3=new String[11];
                try{
                    while(resultSet.next()){
                        System.out.println("resultSet");
                        res3[0]="CLICK_A_SONG";
                        for(int i=1;i<11;i++){
                            res3[i]=resultSet.getString(i);
                        }
                        asong.setAll(res3);
                        String objectXml3=XStreamUtil.objectToXml(asong);
                        ServerSocketUtils.Task.sendToClientXml(objectXml3);
                        //System.out.println(objectXml3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "LOGIN_A_USER":
                try {
                        if(resultSet.next()){
                            //有结果,表示登录成功,返回1
                            ClickOneUserOrSongOrSinger state=new ClickOneUserOrSongOrSinger(RequestEnum.LOGIN_A_USER,1);
                            String objectXml5=XStreamUtil.objectToXml(state);
                            ServerSocketUtils.Task.sendToClientXml(objectXml5);
                            System.out.println("state is 1");
                        } else {
                            // 对应用户登录失败的情况,此时返回0
                            ClickOneUserOrSongOrSinger state=new ClickOneUserOrSongOrSinger(RequestEnum.LOGIN_A_USER,0);
                            String objectXml4=XStreamUtil.objectToXml(state);
                            ServerSocketUtils.Task.sendToClientXml(objectXml4);
                            System.out.println("state is 0");
                        }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            //case "CREATE_A_SONGLIST":
            case "SHOW_MY_SONGLISTS":
                String kind2="SHOW_MY_SONGLISTS";
                String[] res4=new String[8];
                List<ReplySonglistInfo> songlists =new ArrayList<ReplySonglistInfo>();
                String[] resForsongLists=new String[5]; // 每一个类似于loginQuest的类里面都有三个字段
                try{
                    if(resultSet.next()){
                        resultSet.beforeFirst();
                        while(resultSet.next()){
                            //String
                            res4[0]=kind2;
                            for(int i=1;i<=7;i++){
                                res4[i]=resultSet.getString(i);
                                //System.out.println(res2[i]);
                            }
                            ReplySonglistInfo asonglist=new ReplySonglistInfo();
                            asonglist.setRequestKind("SHOW_MY_SONGLISTS");
                            asonglist.setSl_id(resultSet.getString(1));
                            asonglist.setDate(resultSet.getString(2));
                            asonglist.setName(resultSet.getString(4));
                            asonglist.setIs_create(resultSet.getString(7));
                            songlists.add(asonglist);
                        }
                        // 以上部分完成了Song_lists的创建
                        if(songlists!=null){
                            ReplySonglists replySonglists=new ReplySonglists(type,songlists);
                            String objectXml=XStreamUtil.objectToXml(replySonglists);
                            ServerSocketUtils.Task.sendToClientXml(objectXml);
                        }
                    }

                    //System.out.println(objectXml);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
