package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * RegistRequest类的描述
 * @DATE 2021.12.23
 * @VERSION v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个类可以被写为XML格式,用于处理用户的注册请求,这个类是用来将用户注册时点击的信息发送到服务器上,由服务器插入到数据库当中
 * todo:注意服务器端的SQL语句要顺便把用户的姓名处理好(在user_info表里)
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="RegistRequest")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","id","userName","userPassword","email","phoneNumber"})
public class RegistRequest implements Serializable {
    private int id;
    RequestEnum requestEnum;
    String userName;
    String userPassword;
    String email;
    String phoneNumber;
    public RegistRequest(){};
    public RegistRequest(RequestEnum requestEnum,int id,String userName,String userPassword,String email,String phoneNumber){
        this.email=email;
        this.id=id;
        this.phoneNumber=phoneNumber;
        this.userPassword=userPassword;
        this.userName=userName;
        this.requestEnum=requestEnum;
    }

    public RequestEnum getRequestEnum() {
        return requestEnum;
    }

    public int getId() {
        return id;
    }

    public String getEmaiL() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setRequestEnum(RequestEnum requestEnum) {
        this.requestEnum = requestEnum;
    }

    public void setEmaiL(String emaiL) {
        this.email = emaiL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
