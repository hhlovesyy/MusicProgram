package XmlClassType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ReplySonglistInfo 类的描述
 * @DATE 2021.12.24
 * @VERSION v1.0
 * @author: Zhang Haohan,Zhang Yingying
 * (服务端->客户端)这个类作为用户请求查看歌单列表的时候服务器所返回的每一个歌单的信息,
 * 包括歌单号,歌单日期,歌单描述,歌单名,user_id(有时可能是创建者),是否为创建
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name="ClickOnLoginBtn")
// 控制JAXB绑定类中属性和字段的排序
@XmlType(propOrder={"requestKind","sl_id","date","name","is_create"})
public class ReplySonglistInfo {
    int sl_id;
    String date;
    //String description;
    String name;
    String requestKind;
    //String user_name;
    //int user_id;// 这里面关心的是user的Id以及user的name,这是为了看到歌单的创建者
    int is_create; // 0表示这是一个收藏歌单,1表示这是一个创建歌单
    public ReplySonglistInfo(){};
    public ReplySonglistInfo(String requestKind,String sl_id,String date,String name,String is_create){
        this.requestKind=requestKind;
        if(sl_id!=null)
        this.sl_id=Integer.parseInt(sl_id);
        this.date=date;
        this.name=name;
        if(is_create!=null)
        this.is_create=Integer.parseInt(is_create);
    }

    public void setRequestKind(String requestKind) {
        this.requestKind = requestKind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSl_id(String sl_id) {
        this.sl_id = Integer.parseInt(sl_id);
    }

    public void setIs_create(String is_create) {
        this.is_create = Integer.parseInt(is_create);
    }

    public String getRequestKind() {
        return requestKind;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getSl_id() {
        return sl_id;
    }

    public int getIs_create() {
        return is_create;
    }
}
