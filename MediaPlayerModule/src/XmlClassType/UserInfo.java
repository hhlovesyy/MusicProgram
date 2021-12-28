package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * UserInfo 类的描述
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个类与数据库中的UserInfo类等价,字段也保持一致,但增加了年龄一项
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="UserInfo")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","user_id","user_name","sex","birthdate","address","is_vip","user_description","user_image","is_singer"})
public class UserInfo implements Serializable {
    private String requestEnum;
    private static final long serialVersionUID=1L;
    int user_id;
    String user_name;
    int sex;
    String birthdate;
    String address;
    int is_vip;
    String user_description;
    String user_image;
    int is_singer;

    /**
     *
     * @param sets 一个集合,根据这个集合给所有类中的对象赋值
     */
    public void setAll(String[] sets){
        this.requestEnum=sets[0];
        this.user_id=Integer.parseInt(sets[1]);
        this.user_name=sets[2];
        this.sex=Integer.parseInt(sets[3]);
        this.birthdate=sets[4];
        this.address=sets[5];
        this.is_vip=Integer.parseInt(sets[6]);
        this.user_description=sets[7];
        this.user_image=sets[8];
        this.is_singer=Integer.parseInt(sets[9]);
    }
    //构造函数
    public UserInfo(){
        super();
    }

    public UserInfo(String requestEnum,String user_id,String user_name,String sex,String birthdate,String address,String is_vip,
    String user_description,String user_image,String is_singer){
        this.requestEnum=requestEnum;
        this.address=address;
        this.birthdate=birthdate;
        this.user_id= Integer.parseInt(user_id);
        this.user_name=user_name;
        this.sex= Integer.parseInt(sex);
        this.is_vip= Integer.parseInt(is_vip);
        this.user_description=user_description;
        this.user_image=user_image;
        this.is_singer= Integer.parseInt(is_singer);
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public int getIs_singer() {
        return is_singer;
    }

    public int getIs_vip() {
        return is_vip;
    }

    public int getSex() {
        return sex;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getUser_description() {
        return user_description;
    }

    public String getUser_image() {
        return user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setIs_singer(String is_singer) {
        this.is_singer = Integer.parseInt(is_singer);
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = Integer.parseInt(is_vip);
    }

    public void setSex(String sex) {
        this.sex = Integer.parseInt(sex);
    }

    public void setUser_id(String user_id) {
        this.user_id = Integer.parseInt(user_id);
    }
}
