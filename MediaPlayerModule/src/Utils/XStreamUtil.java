package Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * XStreamUtil类的描述
 * @VERSION v1.0
 * @DATE 2021.12.21
 * @author Zhang Haohan,Zhang Yingying
 * 这个类作为工具类用于将任何Object转为XML格式,或者是将任何XML格式的字符串转换为类的对象
 */
public class XStreamUtil{
    /**
     * objectToXml方法的描述
     * @VERSION v1.0
     * @DATE 2021.12.21
     * @author Zhang Haohan,Zhang Yingying
     * @param obj 输入的类,可以是任何请求所表示的类,将这个类转换为XML
     * @return String 返回的是String类型的XNL格式字符串
     */
    public static String objectToXml(Object obj){
        // 参考文章:https://blog.csdn.net/liliang_11676/article/details/81837215
        // 创建输出流
        StringWriter sw=new StringWriter();
        try{
            // 利用jdk当中自带的转换类来实现
            JAXBContext context=JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller=context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj,sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * converXmlStrToObject方法的描述
     * @param clazz 想要转换成的类的对象
     * @param xmlStr xml字符串
     * @return 转换后的目标类
     */
    public static Object converXmlStrToObject(Class clazz,String xmlStr){
        Object xmlObject=null;
        try{
            JAXBContext context=JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象和核心接口
            Unmarshaller unmarshaller=context.createUnmarshaller();
            StringReader sr=new StringReader(xmlStr);
            xmlObject=unmarshaller.unmarshal(sr);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }
}

