package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * QuestFan 点击“粉丝”后
 * @DATE 2021.12.24
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (客户端->服务端)这个类是客户端在 点击“粉丝” 的时候发送给服务端的信息 括用户id
 * 希望得到自己的粉丝 包
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnFan")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","user_id"})
public class QuestFan {
    private String requestEnum;
    private int user_id;

    public QuestFan() {
        super();
    }

    public QuestFan(String requestEnum, String user_id) {
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

