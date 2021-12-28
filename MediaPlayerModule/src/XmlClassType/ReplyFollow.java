package XmlClassType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * ReplyFollow
 * @DATE 2021.12.22
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * (服务端->客户端)这个类是客户端在 点击“关注” 的时候后服务端发送回给客户端的“关注列表”的信息
 * 包括用户关注的所有人的信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ReplyFollow")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","follow"})
public class ReplyFollow {
    private String requestEnum;
    private List<UserInfo> follow;

    public ReplyFollow() {super();
    }

    public ReplyFollow(String requestEnum, List<UserInfo> follow) {
        this.requestEnum = requestEnum;
        this.follow = follow;
    }

    public List<UserInfo> getFollow() {
        return follow;
    }

    public void setFollow(List<UserInfo> follow) {
        this.follow = follow;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }
}
