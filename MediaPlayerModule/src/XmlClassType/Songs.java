package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Songs 歌曲类的描述
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * 这个类与数据库中的Song类等价,字段也保持一致
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnSongInfo")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","song_id","song_name","songtxt_addr","singer_id","is_VIP","song_last","album_id","songmus_addr","listen_cnt","comment_cnt"})

public class Songs implements Serializable {
    private static final long serialVersionUID=1L;
    private String requestEnum;
    private int song_id;
    private String song_name;
    private String songtxt_addr;
    private int singer_id;
    private int is_VIP;
    private int song_last;
    private int album_id;
    private String songmus_addr;
    private int listen_cnt;
    private int comment_cnt;

    public void setAll(String[] sets)
    {
        this.requestEnum=sets[0];
        this.song_id=Integer.parseInt(sets[1]);
        this.song_name=sets[2];
        this.songtxt_addr=sets[3];
        this.singer_id= Integer.parseInt(sets[4]);
        this.is_VIP= Integer.parseInt(sets[5]);
        this.song_last=Integer.parseInt(sets[6]);
        this.album_id=Integer.parseInt(sets[7]);
        this.songmus_addr=sets[8];
        this.listen_cnt=Integer.parseInt(sets[9]);
        this.comment_cnt=Integer.parseInt(sets[10]);
    }
    public Songs()
    {
        super();
    }

    public Songs(String requestEnum, String song_id, String song_name,
                 String songtxt_addr, String singer_id,String is_VIP,
                 String song_last, String album_id, String songmus_addr,
                 String listen_cnt, String comment_cnt) {
        this.requestEnum = requestEnum;
        this.song_id = Integer.parseInt(song_id) ;
        this.song_name = song_name;
        this.songtxt_addr = songtxt_addr;
        this.singer_id =Integer.parseInt( singer_id);
        this.is_VIP =Integer.parseInt(is_VIP) ;
        this.song_last = Integer.parseInt(song_last);
        this.album_id =Integer.parseInt(album_id) ;
        this.songmus_addr = songmus_addr;
        this.listen_cnt =Integer.parseInt(listen_cnt) ;
        this.comment_cnt = Integer.parseInt(comment_cnt);
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public int getListen_cnt() {
        return listen_cnt;
    }

    public void setListen_cnt( int listen_cnt) {
        this.listen_cnt = listen_cnt;
    }

    public String getSongmus_addr() {
        return songmus_addr;
    }

    public void setSongmus_addr(String songmus_addr) {
        this.songmus_addr = songmus_addr;
    }

    public  int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id( int album_id) {
        this.album_id = album_id;
    }

    public  int getSong_last() {
        return song_last;
    }

    public void setSong_last( int song_last) {
        this.song_last = song_last;
    }

    public  int getIs_VIP() {
        return is_VIP;
    }

    public void setIs_VIP( int is_VIP) {
        this.is_VIP = is_VIP;
    }

    public  int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id( int singer_id) {
        this.singer_id = singer_id;
    }

    public String getSongtxt_addr() {
        return songtxt_addr;
    }

    public void setSongtxt_addr(String songtxt_addr) {
        this.songtxt_addr = songtxt_addr;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public  int getSong_id() {
        return song_id;
    }

    public void setSong_id( int song_id) {
        this.song_id = song_id;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }
}
