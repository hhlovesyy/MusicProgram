package cn.itheima.utils;

import cn.itheima.media.SoundBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXNotRecognizedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLUtils {
    //1.读取所有已创建的歌单
    public static List<String> readAllGroup(){
        //1.创建一个List<String>集合对象
        List<String> groupList = new ArrayList<>();
        //2.创建一个SAXReader对象
        SAXReader reader = new SAXReader();
        //3.读取Document对象
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("MusicGroup.xml"));
            //4.读取根元素
            Element root = dom.getRootElement();
            if (root == null) {
                return groupList;
            }
            //5.读取所有根元素下的<group>子元素
            List<Element> groupEleList = root.elements("group");
            if (groupEleList == null || groupEleList.size() == 0) {
                return groupList;
            }
            //6.遍历每个<group>元素，获取它的name属性的值
            for (Element ele : groupEleList) {
                groupList.add(ele.attributeValue("name"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    //2.像MusicGroup.xml中添加一个"新歌单"
    public static void addGroup(String groupName) {
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("MusicGroup.xml"));
            //读取根元素
            Element root = dom.getRootElement();
            //向根元素下添加一个新的<group>元素
            Element groupEle = root.addElement("group");
            groupEle.addAttribute("name", groupName);
            groupEle.addAttribute("addDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setExpandEmptyElements(true);//生成完整标签<group></group>而不是：<group/>

//            path	/MediaPlayerPro/MediaPlayerModule/src
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("MediaPlayerModule/src/MusicGroup.xml")),outputFormat);
//            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("src/MusicGroup.xml")),outputFormat);
            xmlWriter.write(dom);
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3.向MusicGroup.xml中写入"某个歌单"的"歌曲"
    public static void insertSounds(String groupName, List<File> fileList) {
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("MusicGroup.xml"));
            Element root = dom.getRootElement();
            List<Element> groupEleList = root.elements("group");
            for (Element ele : groupEleList) {
                if (ele.attributeValue("name").equals(groupName)) {//找到要添加歌曲的<group>标签
                    for (File file : fileList) {
                        Element soundEle = ele.addElement("sound");
                        Element filePathEle = soundEle.addElement("filePath");
                        Element addDateEle = soundEle.addElement("addDate");

                        filePathEle.setText(file.getAbsolutePath());
                        addDateEle.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


                    }
                    break;
                }
            }

            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setExpandEmptyElements(true);//生成完整标签<group></group>而不是：<group/>
            
            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("MediaPlayerModule/src/MusicGroup.xml")),outputFormat);
//            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("src/MusicGroup.xml")),outputFormat);
            xmlWriter.write(dom);
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除分组
    public static void deleteGroup(String groupName) {
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("MusicGroup.xml"));
            Element root = dom.getRootElement();
            List<Element> groupEleList = root.elements("group");
            for (Element ele : groupEleList) {
                if (ele.attributeValue("name").equals(groupName)) {//找到要添加歌曲的<group>标签
                    //调用父元素的删除方法
                    root.remove(ele);
                    break;
                }
            }

            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setExpandEmptyElements(true);//生成完整标签<group></group>而不是：<group/>

            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("MediaPlayerModule/src/MusicGroup.xml")),outputFormat);
//            XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File("src/MusicGroup.xml")),outputFormat);
            xmlWriter.write(dom);
            xmlWriter.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取上次关闭时的播放信息
    public static String[] readPrevPlayInfo(){
        SAXReader reader = new SAXReader();
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("playInfo.xml"));
            //读取根元素
            Element root = dom.getRootElement();
            if (root == null) {
                return null;
            }
            //读取下面的<currentGroup>子元素
            Element ele = root.element("currentGroup");
            if (ele == null) {
                return null;
            }
            String groupName = ele.attributeValue("name");
            //读取<currentGroup>元素下面的<currentIndex>子元素
            Element indexEle = ele.element("currentIndex");
            if (indexEle == null) {
                return null;
            }
            String index = indexEle.getText();

            String[] strArray = new String[2];
            strArray[0] = groupName;
            strArray[1] = index;

            return strArray;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;

    }

    //读取某个歌单的所有歌曲
    public static List<SoundBean> findSoundByGroupName(String groupName) {
        SAXReader reader = new SAXReader();
        List<SoundBean> soundList = new ArrayList<>();
        try {
            Document dom = reader.read(XMLUtils.class.getClassLoader().getResourceAsStream("MusicGroup.xml"));
            Element root = dom.getRootElement();
            List<Element> eleList = root.elements("group");
            for (Element ele : eleList) {
                if (ele.attributeValue("name").equals(groupName)) {
                    //获取他下面所有的<sound>子元素
                    List<Element> soundEleList = ele.elements("sound");
                    //遍历
                    for (Element soundEle : soundEleList) {
                        SoundBean soundBean = new SoundBean();
                        soundBean.setFilePath(soundEle.elementText("filePath"));
                        soundBean.setAddDate(soundEle.elementText("addDate"));
                        soundList.add(soundBean);
                    }
                    return soundList;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return soundList;
    }
}
