package Utils;

import TestThings.MainAppTest1ForClickOne;
import XmlClassType.*;
import media.MusicLabel;
import media.MusicPanel;

/**
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个类专门对用户点击事件的各种情况进行处理
 */
public class EventHandling {
    public String requestEnum;

    /**
     * 不同的函数会为这里传递参数,这里来决定如何去做
     * 一般来说与查询有关的问题采用MusicLabel的方法,如果不是的话先抛给其他的函数来处理吧
     * @param requestEnum
     */
    public static void decideRequest(String requestEnum,MusicLabel obj){
        String newXmlStream;
        switch (requestEnum){
            case "CLICK_A_USER":
                ClickOneUserOrSongOrSinger userinfo=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_A_USER,obj.getLabelId());
                String xmlStream= XStreamUtil.objectToXml(userinfo);
                ClientSocketUtils.sendToServerXml(xmlStream);
                break;
            case "CLICK_A_SONGLIST":
                ClickOneUserOrSongOrSinger clicksonglist=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_A_SONGLIST,obj.getLabelId());
                String xmlStream2= XStreamUtil.objectToXml(clicksonglist);
                ClientSocketUtils.sendToServerXml(xmlStream2);
                break;
            case "CLICK_A_SONG":
                ClickOneUserOrSongOrSinger clicksong=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_A_SONG,obj.getLabelId());
                String xmlStream3=XStreamUtil.objectToXml(clicksong);
                ClientSocketUtils.sendToServerXml(xmlStream3);
                break;
            case "DO_NOTHING":
                break;
            case "CLICK_AN_ALBUM":
                ClickOneUserOrSongOrSinger clickalbum=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_AN_ALBUM,obj.getLabelId());
                String xmlStream4=XStreamUtil.objectToXml(clickalbum);
                ClientSocketUtils.sendToServerXml(xmlStream4);
                break;
            case "SHOW_MY_SONGLIST":
                ClickOneUserOrSongOrSinger showMySongList=new ClickOneUserOrSongOrSinger(RequestEnum.SHOW_MY_SONGLISTS,MainAppTest1ForClickOne.userId);
                newXmlStream=XStreamUtil.objectToXml(showMySongList);
                ClientSocketUtils.sendToServerXml(newXmlStream);
        }
    }

    public static void giveOutRequest(String requestEnum, MusicPanel musicPanel){
        String xmlStreamnew;
        switch(requestEnum){
            case "REGIST":
                String[] collectinfo=musicPanel.getCollectInfo();
                RegistRequest registRequest=new RegistRequest(RequestEnum.REGIST_A_USER,-1,collectinfo[0],collectinfo[1],collectinfo[2],collectinfo[3]);
                String xmlStream=XStreamUtil.objectToXml(registRequest);
                ClientSocketUtils.sendToServerXml(xmlStream);
                //System.out.println(xmlStream);
                break;
            case "LOGIN":
                String[] collectinfo2=musicPanel.getCollectInfo();
                LoginQuest loginQuest=new LoginQuest("LOGIN_A_USER",collectinfo2[0],collectinfo2[1]);
                String xmlStream2=XStreamUtil.objectToXml(loginQuest);
                ClientSocketUtils.sendToServerXml(xmlStream2);
                break;
            case "CREATE_A_SONGLIST":
                String[] collectinfo3=musicPanel.getCollectInfo();
                QuestAddGroup addSonglist=new QuestAddGroup("CREATE_A_SONGLIST", collectinfo3[0],collectinfo3[1]);
                xmlStreamnew=XStreamUtil.objectToXml(addSonglist);
                ClientSocketUtils.sendToServerXml(xmlStreamnew);
                break;
            case "SEARCH":

                break;
        }
    }


}
