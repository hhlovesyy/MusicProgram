package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * ClickAThing 针对点击用户,点击一首歌,点击一个歌手时候的操作
 * @VERSION v1.0
 * @DATE 2021.12.21
 * @author Zhang Haohan,Zhang Yingying
 * 这里面的字段是点击以上内容时的请求类型和相关的id
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOneUserOrSongOrAlbum")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","requestId"})
public class ClickOneUserOrSongOrSinger implements Serializable {
    private static final long serialVersionUID=1L;
    private RequestEnum requestEnum;
    private int requestId;
    // int userId; //用户自己的id,可能要用作校验使用,核对是否是该用户所发的请求

    public ClickOneUserOrSongOrSinger(RequestEnum requestEnum,int requestId){
        super();
        this.requestEnum=requestEnum;
        this.requestId=requestId;
    }
    public ClickOneUserOrSongOrSinger() {
        super();
    }

    public int getRequestId() {
        return requestId;
    }

    public RequestEnum getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(RequestEnum requestEnum) {
        this.requestEnum = requestEnum;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
