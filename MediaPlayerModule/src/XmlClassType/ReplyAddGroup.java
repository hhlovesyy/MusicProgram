package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ReplyAddGroup 回复新建歌单的歌单id
 * @DATE 2021.12.23
 * @VERSION v1.0
 * @author Zhang Yingying，Zhang Haohan
 * 服务器向客户端传送的新建的歌单的id
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="SReplyAddGroup")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder = {"requestEnum","sl_id"})
public class ReplyAddGroup {

    private String requestEnum;
    private int sl_id;



    public ReplyAddGroup() {
        super();
    }

    public ReplyAddGroup(String requestEnum, String sl_id) {
        this.requestEnum = requestEnum;
        this.sl_id = Integer.parseInt(sl_id);
    }
    public void SetAll(String[] sets)
    {
        this.requestEnum = sets[0];
        this.sl_id = Integer.parseInt(sets[1]);
    }

    public int getSl_id() {
        return sl_id;
    }

    public void setSl_id(int sl_id) {
        this.sl_id = sl_id;
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum;
    }
}