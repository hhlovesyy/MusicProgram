package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 *  LoginQuest 请求登录类的描述
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (客户端->服务端)这个类是客户端在登陆的时候发送给服务端的信息 包括账号和密码
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnLoginBtn")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","user_id","password"})
public class LoginQuest implements Serializable {
    private String requestEnum;
    private static final long serialVersionUID=1L;
    private int user_id;
    private String password;

    public LoginQuest(String requestEnum, String user_id, String password) {
        this.requestEnum = requestEnum;
        this.user_id = Integer.parseInt(user_id);
        this.password = password;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }

    public LoginQuest()
    {
        super();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void SetAll(String[] sets)
    {
        this.requestEnum=sets[0];
        this.user_id=Integer.parseInt(sets[1]);
        this.password=sets[2];
    }

}