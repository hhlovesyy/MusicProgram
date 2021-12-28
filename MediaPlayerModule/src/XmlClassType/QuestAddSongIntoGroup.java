package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * QuestAddGroup 请求在歌单中加入歌曲
 * @DATE 2021.12.23
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (客户端->服务端)这个类是客户端在创建在歌曲上点击收藏的加号，然后加入自己的某个歌单的时候发送给服务端的信息 包括本人id，歌单id和歌曲id
 *需要知道用户id是因为 如果不是自己的歌单无法添加歌曲
 */

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnAddSongIntoGroup")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","user_id","sl_id","song_id"})
public class QuestAddSongIntoGroup {
    private String requestEnum;
    private int user_id;
    private int sl_id;
    private int song_id;

    public QuestAddSongIntoGroup() {
        super();
    }

    public QuestAddSongIntoGroup(String requestEnum, String user_id, String sl_id, String song_id) {
        this.requestEnum = requestEnum;
        this.user_id = Integer.parseInt(user_id);
        this.sl_id = Integer.parseInt(sl_id);
        this.song_id = Integer.parseInt(song_id);
    }
    public void SetAll(String[] sets) {
        this.requestEnum = sets[0];
        this.user_id = Integer.parseInt(sets[1]);
        this.sl_id = Integer.parseInt(sets[2]);
        this.song_id = Integer.parseInt(sets[3]);
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getSl_id() {
        return sl_id;
    }

    public void setSl_id(int sl_id) {
        this.sl_id = sl_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }
}