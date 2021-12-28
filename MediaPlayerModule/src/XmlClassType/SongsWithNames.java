package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * SongsWithNames 类的描述
 * @DATE 2021.12.23
 * @VERSION v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个类用于存储包含歌曲名等信息的歌曲类
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOneUserOrSongOrAlbum")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestKind","songName","singerName","albumName","asong"})
public class SongsWithNames implements Serializable {
    Songs asong;
    String songName;
    String singerName;
    String albumName;
    String requestKind;
    public SongsWithNames(){}
    public SongsWithNames(String requestKind,Songs asong,String songName,String singerName,String albumName){
        this.requestKind=requestKind;
        this.albumName=albumName;
        this.songName=songName;
        this.singerName=singerName;
        this.asong=asong;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongName() {
        return songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public Songs getAsong() {
        return asong;
    }

    public String getRequestKind() {
        return requestKind;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setAsong(Songs asong) {
        this.asong = asong;
    }

    public void setRequestKind(String requestKind) {
        this.requestKind = requestKind;
    }
}
