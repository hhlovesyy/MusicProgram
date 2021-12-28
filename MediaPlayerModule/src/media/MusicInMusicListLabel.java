package media;

import javafx.scene.paint.Color;

/**
 * 这个类用来指显示为歌单的一行歌曲的label
 */
public class MusicInMusicListLabel {
    String songName;
    int songId;
    String singerName;
    int singerId;
    String albumName;
    int albumId;
    int songLast;
    MusicLabel musicLabel1;
    MusicLabel musicLabel2;
    MusicLabel musicLabel3;
    MusicLabel musicLabel4;

    int locateX;
    int locateY;
    public MusicInMusicListLabel(){};
    public MusicInMusicListLabel(String songName,int songId,String singerName,int singerId,
                                 String albumName,int albumId,int songLast){
        this.albumId=albumId;
        this.albumName=albumName;
        this.songLast=songLast;
        this.songName=songName;
        this.singerId=singerId;
        this.singerName=singerName;
        this.songId=songId;
        //init();
    }
    public void setLocate(int x,int y){
        locateX=x;
        locateY=y;
    }
    // 产生一些包含信息的label,方便用户点击查看
    public void init(){
       musicLabel1=new MusicLabel(songName,"SONG",songId);
       musicLabel1.getLabel().setTextFill(Color.WHITE);
       musicLabel1.getLabel().setPrefWidth(80);
       musicLabel1.getLabel().setPrefHeight(10);
       musicLabel1.getLabel().setLayoutX(locateX);
       musicLabel1.getLabel().setLayoutY(locateY);
       musicLabel1.init();

       musicLabel2=new MusicLabel(singerName,"USER",singerId);
        musicLabel2.getLabel().setTextFill(Color.WHITE);
        musicLabel2.getLabel().setPrefWidth(80);
        musicLabel2.getLabel().setPrefHeight(10);
        musicLabel2.getLabel().setLayoutX(locateX+100);
        musicLabel2.getLabel().setLayoutY(locateY);
        musicLabel2.init();

        musicLabel3=new MusicLabel(albumName,"ALBUMLIST",albumId);
        musicLabel3.getLabel().setTextFill(Color.WHITE);
        musicLabel3.getLabel().setPrefWidth(80);
        musicLabel3.getLabel().setPrefHeight(10);
        musicLabel3.getLabel().setLayoutX(locateX+200);
        musicLabel3.getLabel().setLayoutY(locateY);
        musicLabel3.init();

        String stringsonglast=Integer.toString(songLast);
        musicLabel4=new MusicLabel(stringsonglast,"NON_HCI",-100);
        musicLabel4.getLabel().setTextFill(Color.WHITE);
        musicLabel4.getLabel().setPrefWidth(80);
        musicLabel4.getLabel().setPrefHeight(10);
        musicLabel4.getLabel().setLayoutX(locateX+300);
        musicLabel4.getLabel().setLayoutY(locateY);
        musicLabel4.init();

    }

    public MusicLabel getMusicLabel4() {
        return musicLabel4;
    }

    public MusicLabel getMusicLabel1() {
        return musicLabel1;
    }

    public MusicLabel getMusicLabel2() {
        return musicLabel2;
    }

    public MusicLabel getMusicLabel3() {
        return musicLabel3;
    }

    public String getSingerName() {
        return singerName;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getSongLast() {
        return songLast;
    }

    public int getSingerId() {
        return singerId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSongLast(int songLast) {
        this.songLast = songLast;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
