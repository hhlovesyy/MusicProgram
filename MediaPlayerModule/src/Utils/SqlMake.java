package Utils;

import XmlClassType.ClickOneUserOrSongOrSinger;
import XmlClassType.LoginQuest;
import XmlClassType.QuestAddGroup;
import XmlClassType.RegistRequest;

/**
 * SqlMake 这个类专门用来将类型转换为对应的SQL查询语句
 * @VERSION v1.0
 * @DATE 2021.12.22
 * @author Zhang Haohan,Zhang Yingying
 * 这个类用于将xml请求转换为sql语句
 */
public class SqlMake {

    static String sqlMake;
    //todo:关于Id的分配可能会涉及到高并发的问题,此时必须要进行考虑,后续会对这个问题进行更新(同一个Id给了两个用户)
    //todo:这个userIdCnt,songlistCnt可能需要通过数据库来存储,因为每次运行程序的时候都会重置这个userIdCnt,这里会引入一些问题
    static int userIdCnt=100;//这里决定userId应该分配到哪一个
    static int songlistCnt=30;
    // 将xml请求转换为sql语句
    public static void convertToSql(String type,String xml){
        System.out.println("进入convertToSql");
        switch (type) {
            case "CLICK_A_USER":
                ClickOneUserOrSongOrSinger user1=(ClickOneUserOrSongOrSinger)XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                String sql="SELECT * FROM user_info WHERE user_id="+user1.getRequestId();
                System.out.println(sql);
                //String sqlinxml="SELECT DBMS_XMLGEN.GETXML('"+sql+"') FROM DUAL";
                //String sqlinxml="SELECT DBMS_XMLGEN.GETXML('SELECT * FROM user_info') FROM DUAL";
                //System.out.println(sqlinxml);
                ConnectDataBaseAndExecute databaseconn=new ConnectDataBaseAndExecute();
                if(databaseconn.connectDatabase()){
                    databaseconn.executeSqlQuery(type,sql);
                    databaseconn.closeDatabase();
                }
                break;
            case "CLICK_A_SONGLIST":
                ClickOneUserOrSongOrSinger user2=(ClickOneUserOrSongOrSinger)XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                String sql2="SELECT song_list.sl_id,singer.singer_name,album.album_name,songs.*\n" +
                        "FROM song_list,singer,songs,songlist_storage,album\n" +
                        "WHERE singer.user_id=songs.singer_id\n" +
                        "AND songs.song_id=songlist_storage.song_id\n" +
                        "AND song_list.sl_id=songlist_storage.sl_id\n" +
                        "AND album.album_id=songs.album_id\n" +
                        "AND song_list.sl_id="+user2.getRequestId();
                System.out.println(sql2);
                ConnectDataBaseAndExecute databaseconn2=new ConnectDataBaseAndExecute();
                if(databaseconn2.connectDatabase()){
                    databaseconn2.executeSqlQuery(type,sql2);
                    databaseconn2.closeDatabase();
                }
                break;
            case "CLICK_A_SONG":
                ClickOneUserOrSongOrSinger user3=(ClickOneUserOrSongOrSinger)XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                String sql3="SELECT * FROM songs WHERE song_id= "+user3.getRequestId();
                System.out.println(sql3);
                ConnectDataBaseAndExecute databaseconn3=new ConnectDataBaseAndExecute();
                if(databaseconn3.connectDatabase()){
                    databaseconn3.executeSqlQuery(type,sql3);
                    databaseconn3.closeDatabase();
                }
                break;
            case "CLICK_AN_ALBUM":
                ClickOneUserOrSongOrSinger user4=(ClickOneUserOrSongOrSinger)XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                String sql4="SELECT song_list.sl_id,singer.singer_name,album.album_name,songs.*\n" +
                        "FROM song_list,singer,songs,songlist_storage,album\n" +
                        "WHERE singer.user_id=songs.singer_id\n" +
                        "AND songs.song_id=songlist_storage.song_id\n" +
                        "AND song_list.sl_id=songlist_storage.sl_id\n" +
                        "AND album.album_id=songs.album_id\n" +
                        "AND album.album_id="+user4.getRequestId();
                System.out.println(sql4);
                ConnectDataBaseAndExecute databaseconn4=new ConnectDataBaseAndExecute();
                if(databaseconn4.connectDatabase()){
                    databaseconn4.executeSqlQuery(type,sql4);
                }
                break;
            case "REGIST_A_USER":
                RegistRequest user5=(RegistRequest) XStreamUtil.converXmlStrToObject(RegistRequest.class,xml);
                sqlMake="INSERT INTO user_base VALUES ("+(++userIdCnt)+",'"+user5.getUserPassword()+"','"+user5.getEmaiL()+"','"
                        +user5.getPhoneNumber()+"')";
                String sqlMake2="INSERT INTO user_info(user_id,user_name) VALUES ("+(userIdCnt)+",'"+user5.getUserName()+"')";
                ConnectDataBaseAndExecute databaseconn5=new ConnectDataBaseAndExecute();
                ConnectDataBaseAndExecute databaseconn6=new ConnectDataBaseAndExecute();
                DecideClassToMakeXml.decideClassToAccept("REGIST_ID",userIdCnt);
                System.out.println(sqlMake);
                //System.out.println(sqlMake2);
                if(databaseconn5.connectDatabase()){
                    databaseconn5.executeSqlQuery("INSERT_USERBASE",sqlMake);
                    databaseconn5.closeDatabase();
                }
                if(databaseconn6.connectDatabase()){
                    databaseconn6.executeSqlQuery("INSERT_USERINFO",sqlMake2);
                    databaseconn6.closeDatabase();
                }
                break;
            case "LOGIN_A_USER":
                LoginQuest login=(LoginQuest) XStreamUtil.converXmlStrToObject(LoginQuest.class,xml);
                sqlMake="SELECT * FROM user_base WHERE user_id="+login.getUser_id()+" AND password='"+login.getPassword()+"'";
                System.out.println(sqlMake);
                ConnectDataBaseAndExecute databaseconn7=new ConnectDataBaseAndExecute();
                if(databaseconn7.connectDatabase()){
                    databaseconn7.executeSqlQuery(type,sqlMake);
                    databaseconn7.closeDatabase();
                }
                break;
            case "CREATE_A_SONGLIST":
                QuestAddGroup addGroup=(QuestAddGroup) XStreamUtil.converXmlStrToObject(QuestAddGroup.class,xml);
                sqlMake="INSERT INTO user_songlist VALUES("+addGroup.getUser_id()+","+(++songlistCnt)+","+1+")";
                String sqlMake3="INSERT INTO song_list(sl_id,sl_birthdate,sl_name) VALUES("+songlistCnt+",null,'"+addGroup.getGroup_name()+"')";
                System.out.println(sqlMake);
                System.out.println(sqlMake3);

                ConnectDataBaseAndExecute databaseconn9=new ConnectDataBaseAndExecute();
                if(databaseconn9.connectDatabase()){
                    databaseconn9.executeSqlQuery("INSERT_SONGLIST",sqlMake3);
                    databaseconn9.closeDatabase();
                }
                ConnectDataBaseAndExecute databaseconn8=new ConnectDataBaseAndExecute();
                if(databaseconn8.connectDatabase()){
                    databaseconn8.executeSqlQuery("INSERT_USERSONGLIST",sqlMake);
                    databaseconn8.closeDatabase();
                }
                DecideClassToMakeXml.decideClassToAccept("SONGLIST_ID",songlistCnt);
                break;
            case "SHOW_MY_SONGLISTS":
                ClickOneUserOrSongOrSinger showresult=(ClickOneUserOrSongOrSinger) XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                sqlMake="SELECT * FROM song_list,user_songlist WHERE song_list.sl_id=user_songlist.sl_id AND user_songlist.user_id="+showresult.getRequestId();
                ConnectDataBaseAndExecute databaseconn12=new ConnectDataBaseAndExecute();
                if(databaseconn12.connectDatabase()){
                    databaseconn12.executeSqlQuery("SHOW_MY_SONGLISTS",sqlMake);
                    databaseconn12.closeDatabase();
                }
                break;
       }
    }
}
