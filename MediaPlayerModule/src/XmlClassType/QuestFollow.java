package XmlClassType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * QuestFollow 点击“关注”后
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (客户端->服务端)这个类是客户端在 点击“关注” 的时候发送给服务端的信息
 * 希望得到自己关注的人 包括用户id
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnFollow")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","user_id"})
public class QuestFollow {
    private String requestEnum;
    private int user_id;

    public QuestFollow() {
        super();
    }

    public QuestFollow(String requestEnum, String user_id) {
        this.requestEnum = requestEnum;
        this.user_id = Integer.parseInt(user_id);
    }
    public void SetAll(String[] sets)
    {
        this.requestEnum=sets[0];
        this.user_id=Integer.parseInt(sets[1]);
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
