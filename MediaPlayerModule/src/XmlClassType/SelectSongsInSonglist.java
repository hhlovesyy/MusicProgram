package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * SelectSongsInSonglist 类的描述
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个类用来处理用户点击歌单这个操作,返回的将是歌单中有哪些歌曲
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="SelectSongsInSonglist")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","sl_id","songs"})
public class SelectSongsInSonglist implements Serializable {
    private String requestEnum;
    //private String songName;
    //private String albumName;
    //private String songLast;
    //private int singerId;
    //private String singerName;
    //private int albumId;
    private int sl_id; // 歌单号sl_id
    private List<SongsWithNames> songs;

    public SelectSongsInSonglist(String requestEnum,String sl_id,List<SongsWithNames> songs){
        this.requestEnum=requestEnum;
        if(sl_id!=null)
        this.sl_id=Integer.parseInt(sl_id);
        this.songs=songs;
    }
//    public SelectSongsInSonglist(String requestEnum,String sl_id,String albumId,String singerId,String singerName,String albumName){
//        this.requestEnum=requestEnum;
//        this.albumId=Integer.parseInt(albumId);
//        this.singerId=Integer.parseInt(singerId);
//        this.singerName=singerName;
//        this.albumName=albumName;
//        this.songName=songName;
//        this.sl_id=Integer.parseInt(sl_id);
//    }

//    public int getSingerId() {
//        return singerId;
//    }
//
//    public void setSingerId(String singerId) {
//        this.singerId = Integer.parseInt(singerId);
//    }
//
//    public String getSingerName() {
//        return singerName;
//    }
//
//    public void setSingerName(String singerName) {
//        this.singerName = singerName;
//    }
//
//    public String getAlbumName() {
//        return albumName;
//    }
//
//    public void setSongLast(String songLast) {
//        this.songLast = songLast;
//    }
//
//    public String getSongLast() {
//        return songLast;
//    }
//
//    public void setAlbumName(String albumName) {
//        this.albumName = albumName;
//    }
//
//    public int getAlbumId() {
//        return albumId;
//    }
//
//    public void setAlbumId(String albumId) {
//        this.albumId = Integer.parseInt(albumId);
//    }
//
//    public String getSongName() {
//        return songName;
//    }
//
//    public void setSongName(String songName) {
//        this.songName = songName;
//    }

    public SelectSongsInSonglist(){}

    public String getRequestEnum() {
        return requestEnum;
    }

    public List<SongsWithNames> getSongs() {
        return songs;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }

    public void setSl_id(String sl_id) {
        this.sl_id = Integer.parseInt(sl_id);
    }

    public int getSl_id() {
        return sl_id;
    }

    public void setSongs(List<SongsWithNames> songs) {
        this.songs = songs;
    }
}
