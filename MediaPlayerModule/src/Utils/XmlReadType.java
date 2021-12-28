package Utils;

import TestThings.MainAppTest1ForClickOne;
import XmlClassType.RequestEnum;
import javafx.application.Platform;
import media.ShowUserInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * XmlReadType 这个类专门用来读取xml数据包的类型,并决定用哪个类来接收
 * @VERSION v1.0
 * @DATE 2021.12.22
 * @author Zhang Haohan,Zhang Yingying
 * 这个类用于读取xml字符串的首部,确定请求类型并交给不同的类进行处理
 */
public class XmlReadType {
    RequestEnum requestEnum;
    public XmlReadType(){
        super();
    }
    /**
     * readType 方法描述
     * @VERSION v1.0
     * @DATE 2021.12.22
     * @author Zhang Haohan,Zhang Yingying
     * 这个静态方法用于确定请求的类型,进而决定交给哪个类来处理
     * 参考文章:https://blog.csdn.net/weixin_34167043/article/details/92266846
     * @note:这是服务器需要加载的类,用于读取客户端所发来的xml的类型
     */
    public static void readTypeForServer(String xml) {
        try {
            System.out.println(xml);
            Document dom=DocumentHelper.parseText(xml);
            Element root=dom.getRootElement();
            String requestKind=root.element("requestEnum").getText();
            System.out.println(requestKind);
            // 根据请求类型让不同的类去进行解析
            SqlMake.convertToSql(requestKind,xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void readTypeForClient(String xml){
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println(xml);
                    Document dom= null;
                    try {
                        dom = DocumentHelper.parseText(xml);
                        Element root=dom.getRootElement();
                        String requestKind=root.element("requestEnum").getText();
                        System.out.println(requestKind);
                        // 根据请求类型让不同的类去进行解析
                        new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).displayInfo(requestKind,xml);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
