package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * ReplySonglists 类的描述
 * @DATE 2021.12.24
 * @version v1.0
 * @author Zhang Haohan,Zhang Yingying
 * 这个是(服务端->客户端的),用来返回所有个歌单信息
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ReplySonglists")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestEnum","replysonglists"})
public class ReplySonglists {
    String requestEnum;
    List<ReplySonglistInfo> replysonglists; //这里面每一个歌单都暂时显示为一个歌单号和一个歌单名字,所以可以复用Login的那个类
    public ReplySonglists(){}
    public ReplySonglists(String requestEnum,List<ReplySonglistInfo> replysonglists){
        this.replysonglists=replysonglists;
        this.requestEnum=requestEnum;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }

    public List<ReplySonglistInfo> getReplysonglists() {
        return replysonglists;
    }

    public void setReplysonglists(List<ReplySonglistInfo> replysonglists) {
        this.replysonglists = replysonglists;
    }
}
