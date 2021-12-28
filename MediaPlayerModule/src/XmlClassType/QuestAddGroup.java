package XmlClassType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * QuestAddGroup 请求创建歌单
 * @DATE 2021.12.23
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (客户端->服务端)这个类是客户端在创建新歌单的时候发送给服务端的信息 包括本人id和歌单名字
 */

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnAddGroup")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","user_id","group_name"})
public class QuestAddGroup implements Serializable{

    private String requestEnum;
    private int user_id;
    private String group_name;

    public QuestAddGroup() {
        super();
    }

    public QuestAddGroup(String requestEnum, String user_id, String group_name) {
        this.requestEnum = requestEnum;
        this.user_id = Integer.parseInt(user_id);
        this.group_name = group_name;
    }
    public void SetAll(String[] sets)
    {
        this.requestEnum=sets[0];
        this.user_id=Integer.parseInt(sets[1]);
    }
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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