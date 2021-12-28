package XmlClassType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * ReplyFan
 * @DATE 2021.12.24
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (服务端->客户端)这个类是客户端在 点击“粉丝” 的时候后服务端发送回给客户端的“粉丝列表”的信息
 * 包括用户的所有粉丝的信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ReplyFan")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","fan"})
public class ReplyFan {
    private String requestEnum;
    private List<UserInfo> fan;

    public  ReplyFan() {super();
    }

    public  ReplyFan(String requestEnum, List<UserInfo> fan) {
        this.requestEnum = requestEnum;
        this.fan = fan;
    }

    public List<UserInfo> getFollow() {
        return fan;
    }

    public void setFollow(List<UserInfo> follow) {
        this.fan = fan;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }
}
