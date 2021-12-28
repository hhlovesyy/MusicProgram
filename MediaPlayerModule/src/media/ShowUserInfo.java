package media;

import TestThings.MainAppTest1ForClickOne;
import Utils.ClientSocketUtils;
import Utils.XStreamUtil;
import XmlClassType.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowUserInfo {
    private Stage parentStage;//父窗体
    private VBox groupVBox;//父窗体中显示歌单列表的VBox对象
    private MainAppTest1ForClickOne mainApp;
    private int userid; //用户的id

    public static Stage staticStage;

    //移动前的x,y坐标
    private double mouseX;
    private double mouseY;

    //本窗体的舞台对象
    private Stage stage;

    public ShowUserInfo(Stage staticStage, VBox groupVBox) {
        this.parentStage = parentStage;
        this.groupVBox = groupVBox;
//        ClickOneUserOrSongOrSinger userinfo=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_A_USER,userid);
//        String xmlStream= XStreamUtil.objectToXml(userinfo);
//        ClientSocketUtils.sendToServerXml(xmlStream);
        //创建一个新舞台对象
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);



    }

    /**
     * 这个函数用于显示我的歌单
     */
    public void showMySonglist(){
        int size=MainAppTest1ForClickOne.songlistlabels.size();
        System.out.println("size="+size);
        Group group = new Group();
        MusicLabel[] labels=new MusicLabel[size];
        for(int i=0;i<size;i++){
            labels[i]=new MusicLabel(MainAppTest1ForClickOne.songlistlabels.get(i).name,"SONGLIST",
                    MainAppTest1ForClickOne.songlistlabels.get(i).getLabelId());
            labels[i].init();
            group.getChildren().add(labels[i].getLabel());
        }
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    /**
     * displayInfo 方法介绍:在客户端显示服务器回传回来的结果
     * @VERSION v1.0
     * @DATE 2021.12.22
     * @author Zhang Haohan,Zhang Yingying
     * @param xml 输入的xml文件解析
     */
    public void displayInfo(String requestKind,String xml){
        System.out.println("enter displayInfo");
        switch(requestKind){
            case "CLICK_A_USER":
                UserInfo userinfo= (UserInfo) XStreamUtil.converXmlStrToObject(UserInfo.class,xml);
                generateUserInfo(userinfo);
                break;
            case "CLICK_A_SONG":
                Songs asong= (Songs) XStreamUtil.converXmlStrToObject(Songs.class,xml);
                generateSongInfo(asong);
                break;
            case "CLICK_A_SONGLIST":
                SelectSongsInSonglist songs=(SelectSongsInSonglist) XStreamUtil.converXmlStrToObject(SelectSongsInSonglist.class,xml);
                generateSongsInSonglist(songs);
                break;
            case "REGIST_A_USER":
            case "LOGIN_A_USER":
            case "CREATE_A_SONGLIST":
                ClickOneUserOrSongOrSinger user1=(ClickOneUserOrSongOrSinger) XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
                generateSingleInfo(user1,requestKind);
                break;
            case "SHOW_MY_SONGLISTS":
                ReplySonglists replySonglists=(ReplySonglists) XStreamUtil.converXmlStrToObject(ReplySonglists.class,xml);
                generateSongLists(replySonglists);
                break;

        }
    }

    /**
     * 这个类用来显示所有的歌单,每个歌单点开可以查看其他的信息,如歌曲等内容
     * @param replySonglists
     */
    public void generateSongLists(ReplySonglists replySonglists){
        System.out.println("enter generateSongsInSongList");
        Group group = new Group();
        for(int i=0;i<replySonglists.getReplysonglists().size();i++){
            if(replySonglists.getReplysonglists().get(i).getIs_create()==0){
                //todo:把这个放到收藏的歌单当中去
            } else {
                MusicLabel musicLabel=new MusicLabel(replySonglists.getReplysonglists().get(i).getName(),"SONGLIST",
                        replySonglists.getReplysonglists().get(i).getSl_id());
                musicLabel.getLabel().setLayoutX(20);
                musicLabel.getLabel().setLayoutY(30+30*i);
                musicLabel.getLabel().setPrefHeight(20);
                musicLabel.getLabel().setPrefWidth(50);
                musicLabel.init();
                group.getChildren().add(musicLabel.getLabel());
            }

        }
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();

    }
    /**
     * 重载displayInfo,以针对于以枚举类传回的情况
     * @note 正常来说这个方法绝对不会被调用
     */
    public void displayInfo(RequestEnum requestKind,String xml){
        if(requestKind==RequestEnum.REGIST_A_USER){
            ClickOneUserOrSongOrSinger user1=(ClickOneUserOrSongOrSinger) XStreamUtil.converXmlStrToObject(ClickOneUserOrSongOrSinger.class,xml);
            generateSingleInfo(user1,requestKind.toString());
        }
    }

    /**
     * 以下函数用来做类似于创建歌单的界面
     * @param requestKind 请求类型
     */
    public void createGenerateWindow(String requestKind){
        MusicPanel musicPanel=new MusicPanel("CREATE_A_SONGLIST",1,2,1,MainAppTest1ForClickOne.userId);
        musicPanel.init();
        Group group = new Group();
        group.getChildren().addAll(musicPanel.getButtons());
        group.getChildren().addAll(musicPanel.getLabels());
        group.getChildren().addAll(musicPanel.getTextFields());
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    /**
     *
     * @param user1 只有一个值得注意的字段的情况
     */
    public void  generateSingleInfo(ClickOneUserOrSongOrSinger user1,String requestKind){
        MusicLabel musicLabel=null;
        switch (requestKind){
            case "REGIST_A_USER":
                musicLabel=new MusicLabel("您的ID为"+user1.getRequestId(),"USER",user1.getRequestId());
                musicLabel.init();
                MainAppTest1ForClickOne.userId=user1.getRequestId();// 为用户的Id进行赋值处理
                break;
            case "LOGIN_A_USER":
                if(user1.getRequestId()==0){
                    musicLabel=new MusicLabel("您的登录状态为:失败,用户id或密码错误","NO_HCI",-10);
                    MainAppTest1ForClickOne.userId=-100;// 登录失败的时候重新将userID设置为原来的.
                } else {
                    musicLabel=new MusicLabel("您的登录状态为:成功,欢迎听歌","NO_HCI",-10);
                    //MainAppTest1ForClickOne.userId=user1.getRequestId();
                }
                musicLabel.init();
                break;
            case "CREATE_A_SONGLIST":
                musicLabel=new MusicLabel("这个歌单号为:"+user1.getRequestId(),"SONGLIST",user1.getRequestId());
                musicLabel.init();
                //todo:在用户歌单的标签当中去添加一个相关的歌单,其中显示如下信息
                MainAppTest1ForClickOne.songlistlabels.add(musicLabel);
                break;
        }
        System.out.println("进入generateSingleInfo函数");
        Group group = new Group();
        group.getChildren().addAll(musicLabel.getLabel());
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();

    }
    /**
     * createRegistWindow方法的描述
     * @DATE 2021.12.23
     * @VERSION v1.0
     * @author Zhang Haohan,Zhang Yingying
     * 这里要产生一个类似panel的东西,这个panel要有m个输入字段和n个按钮
     */
    public void createRegistWindow(String requestKind){
        MusicPanel musicPanel=new MusicPanel(requestKind,4,2,4,-1);
        musicPanel.init();

        Group group = new Group();
        group.getChildren().addAll(musicPanel.getButtons());
        group.getChildren().addAll(musicPanel.getLabels());
        group.getChildren().addAll(musicPanel.getTextFields());
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    public void createSearchWindow(String requestKind){
        MusicPanel musicPanel=new MusicPanel(requestKind,1,2,1,-1);
        musicPanel.init();
        Group group = new Group();
        group.getChildren().addAll(musicPanel.getButtons());
        group.getChildren().addAll(musicPanel.getLabels());
        group.getChildren().addAll(musicPanel.getTextFields());
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    /**
     * 新建一个与登录有关的Panel
     * @param requestKind
     */
    public void createLoginWindow(String requestKind){
        MusicPanel musicPanel=new MusicPanel(requestKind,2,2,2,-1);
        musicPanel.init();
        Group group = new Group();
        group.getChildren().addAll(musicPanel.getButtons());
        group.getChildren().addAll(musicPanel.getLabels());
        group.getChildren().addAll(musicPanel.getTextFields());
        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    public void generateSongsInSonglist(SelectSongsInSonglist songs){
        //创建一个场景
        System.out.println("enter generateSongsInSongList");
        Group group = new Group();
        for(int i=0;i<songs.getSongs().size();i++){
            MusicInMusicListLabel musicInMusicListLabel=new MusicInMusicListLabel(songs.getSongs().get(i).getSongName(),
                    songs.getSongs().get(i).getAsong().getSong_id(),
                    songs.getSongs().get(i).getSingerName(),
                    songs.getSongs().get(i).getAsong().getSinger_id(),
                    songs.getSongs().get(i).getAlbumName(),
                    songs.getSongs().get(i).getAsong().getAlbum_id(),
                    songs.getSongs().get(i).getAsong().getSong_last());
            musicInMusicListLabel.setLocate(20,20+30*(i+1));
            musicInMusicListLabel.init();
            group.getChildren().add(musicInMusicListLabel.getMusicLabel1().getLabel());
            group.getChildren().add(musicInMusicListLabel.getMusicLabel2().getLabel());
            group.getChildren().add(musicInMusicListLabel.getMusicLabel3().getLabel());
            group.getChildren().add(musicInMusicListLabel.getMusicLabel4().getLabel());

            System.out.println(songs.getSongs().get(i).getSingerName());
            System.out.println(songs.getSongs().get(i).getAsong().getSongmus_addr());
        }

        Scene scene = new Scene(group, 1000, 1000);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();

    }
    public void generateSongInfo(Songs asong){
        Label lab1 = new Label("歌曲名:"+asong.getSong_name());
        lab1.setTextFill(Color.WHITE);
        lab1.setPrefWidth(250);
        lab1.setPrefHeight(10);
        lab1.setLayoutX(20);
        lab1.setLayoutY(0);

        Label lab2 = new Label("评论量:"+asong.getComment_cnt());
        lab2.setTextFill(Color.WHITE);
        lab2.setPrefWidth(250);
        lab2.setPrefHeight(10);
        lab2.setLayoutX(20);
        lab2.setLayoutY(20);

        Label lab3 = new Label("歌词地址:"+asong.getSongtxt_addr());
        lab3.setTextFill(Color.WHITE);
        lab3.setPrefWidth(250);
        lab3.setPrefHeight(10);
        lab3.setLayoutX(20);
        lab3.setLayoutY(40);

        Label lab4 = new Label("歌曲地址:"+asong.getSongmus_addr());
        lab4.setTextFill(Color.WHITE);
        lab4.setPrefWidth(250);
        lab4.setPrefHeight(10);
        lab4.setLayoutX(20);
        lab4.setLayoutY(60);

        Label lab5 = new Label("歌曲id:"+asong.getSong_id());
        lab5.setTextFill(Color.WHITE);
        lab5.setPrefWidth(250);
        lab5.setPrefHeight(10);
        lab5.setLayoutX(20);
        lab5.setLayoutY(80);



        //创建一个场景
        Group group = new Group();
        group.getChildren().addAll(lab1,lab2,lab3,lab4,lab5);
        Scene scene = new Scene(group, 300, 240);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();

    }
    public void generateUserInfo(UserInfo userInfo){
        String name1=userInfo.getUser_name();
        String name2=userInfo.getAddress();
        System.out.println("name1===="+name1);
        System.out.println("name2====="+name2);
        Label lab1 = new Label("用户名:"+userInfo.getUser_name());
        lab1.setTextFill(Color.WHITE);
        lab1.setPrefWidth(150);
        lab1.setPrefHeight(10);
        lab1.setLayoutX(20);
        lab1.setLayoutY(0);

        Label lab2 = new Label("用户性别:"+userInfo.getSex());
        lab2.setTextFill(Color.WHITE);
        lab2.setPrefWidth(150);
        lab2.setPrefHeight(10);
        lab2.setLayoutX(20);
        lab2.setLayoutY(20);

        Label lab3 = new Label("用户地址:"+userInfo.getAddress());
        lab3.setTextFill(Color.WHITE);
        lab3.setPrefWidth(150);
        lab3.setPrefHeight(10);
        lab3.setLayoutX(20);
        lab3.setLayoutY(40);

        Label lab4 = new Label("用户资料:"+userInfo.getUser_description());
        lab4.setTextFill(Color.WHITE);
        lab4.setPrefWidth(150);
        lab4.setPrefHeight(10);
        lab4.setLayoutX(20);
        lab4.setLayoutY(60);

        Label lab5 = new Label("用户id:"+userInfo.getUser_id());
        lab5.setTextFill(Color.WHITE);
        lab5.setPrefWidth(150);
        lab5.setPrefHeight(10);
        lab5.setLayoutX(20);
        lab5.setLayoutY(80);



        //创建一个场景
        Group group = new Group();
        group.getChildren().addAll(lab1,lab2,lab3,lab4,lab5);
        Scene scene = new Scene(group, 300, 240);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }
    public ShowUserInfo(Stage parentStage, VBox groupVBox,String s) {
        this.parentStage = parentStage;
        this.groupVBox = groupVBox;
        //this.mainApp = mainApp;
        this.userid=userid;
        ClickOneUserOrSongOrSinger userinfo=new ClickOneUserOrSongOrSinger(RequestEnum.CLICK_A_USER,userid);
        String xmlStream= XStreamUtil.objectToXml(userinfo);
        ClientSocketUtils.sendToServerXml(xmlStream);

        //1.新建歌单：Label
        Label lab1 = new Label("用户名");
        lab1.setTextFill(Color.WHITE);
        lab1.setPrefWidth(150);
        lab1.setPrefHeight(50);
        lab1.setLayoutX(20);
        lab1.setLayoutY(0);

        Label lab100 = new Label("性别");
        lab1.setTextFill(Color.WHITE);
        lab1.setPrefWidth(150);
        lab1.setPrefHeight(50);
        lab1.setLayoutX(20);
        lab1.setLayoutY(0);

        //2.关闭按钮：ImageView


        //3.文本框：TextField
        TextField txtGroupName = new TextField();
        txtGroupName.setPromptText("请输入歌单名称");
        txtGroupName.setPrefWidth(220);
        txtGroupName.setPrefHeight(15);
        txtGroupName.setLayoutX(20);
        txtGroupName.setLayoutY(70);

        //4.提示标签：Label
        Label labMsg = new Label();
        labMsg.setPrefWidth(200);
        labMsg.setLayoutX(20);
        labMsg.setLayoutY(100);
        labMsg.setTextFill(Color.RED);


        //5.确定按钮：Button
        Button butOk = new Button("确定");
        butOk.setPrefWidth(80);
        butOk.setPrefHeight(30);
        butOk.setLayoutX(50);
        butOk.setLayoutY(190);
        butOk.setTextFill(Color.WHITE);
        butOk.setBackground(new Background(new BackgroundFill(Color.rgb(50,45,128),null,null)));
        butOk.setOnMouseClicked(e ->{
            //1.获取文件筐的数据
            String txt = txtGroupName.getText().trim();
            //判断
            if (txt == null || txt.length() == 0) {
                labMsg.setText("请输入歌单名称！");
                return;
            }



            //4.更新主窗体上的VBox列表
            //1.心形图标：ImageView
            ImageView iv1 = new ImageView("img/left/xinyuanDark.png");
            iv1.setFitWidth(15);
            iv1.setPreserveRatio(true);
            Label lab11 = new Label("", iv1);
            lab11.setMinWidth(0);
            lab11.setMinHeight(0);
            lab11.setPrefWidth(15);
            lab11.setPrefHeight(15);
            lab11.setOnMouseEntered(ee -> iv1.setImage(new Image("img/left/xinyuan.png")));
            lab11.setOnMouseExited(ee -> iv1.setImage(new Image("img/left/xinyuanDark.png")));


            //2.歌单名称：Label
            Label labGroupName = new Label(txt);
            labGroupName.setMinHeight(0);
            labGroupName.setPrefHeight(15);
            labGroupName.setPrefWidth(150);
            labGroupName.setTextFill(Color.rgb(210,210,210));
            labGroupName.setOnMouseEntered(ee -> labGroupName.setTextFill(Color.WHITE));
            labGroupName.setOnMouseExited(ee -> labGroupName.setTextFill(Color.rgb(210,210,210)));


            //3.播放图片：ImageView
            ImageView iv2 = new ImageView("img/left/volumn_1_Dark.png");
            iv2.setFitWidth(15);
            iv2.setFitHeight(15);
            Label lab22 = new Label("", iv2);
            lab22.setMinWidth(0);
            lab22.setMinHeight(0);
            lab22.setPrefWidth(15);
            lab22.setPrefHeight(15);
            lab22.setOnMouseEntered(ee -> iv2.setImage(new Image("img/left/volumn_1.png")));
            lab22.setOnMouseExited(ee -> iv2.setImage(new Image("img/left/volumn_1_Dark.png")));


            //4.+符号：ImageView
            ImageView iv3 = new ImageView("img/left/addDark.png");
            iv3.setFitWidth(15);
            iv3.setFitHeight(15);
            Label lab3 = new Label("", iv3);
            lab3.setMinWidth(0);
            lab3.setMinHeight(0);
            lab3.setPrefWidth(15);
            lab3.setPrefHeight(15);
            lab3.setOnMouseEntered(ee -> iv3.setImage(new Image("img/left/add.png")));
            lab3.setOnMouseExited(ee -> iv3.setImage(new Image("img/left/addDark.png")));

            //5.垃圾桶符号：ImageView
            ImageView iv4 = new ImageView("img/left/laji_1_Dark.png");
            iv4.setFitWidth(15);
            iv4.setFitHeight(15);
            Label lab4 = new Label("", iv4);
            lab4.setMinWidth(0);
            lab4.setMinHeight(0);
            lab4.setPrefWidth(15);
            lab4.setPrefHeight(15);
            lab4.setOnMouseEntered(ee -> iv4.setImage(new Image("img/left/laji_1.png")));
            lab4.setOnMouseExited(ee -> iv4.setImage(new Image("img/left/laji_1_Dark.png")));

            HBox hBox1 = new HBox(10);
            hBox1.getChildren().addAll(lab11, labGroupName, lab22, lab3, lab4);
            hBox1.setPadding(new Insets(5,5,5,10));
            this.groupVBox.getChildren().add(hBox1);

            //关闭此舞台
            this.stage.hide();
        });

        //6.取消按钮：Button
        Button butCancel = new Button("取消");
        butCancel.setPrefWidth(80);
        butCancel.setPrefHeight(30);
        butCancel.setLayoutX(150);
        butCancel.setLayoutY(190);
        butCancel.setTextFill(Color.WHITE);
        butCancel.setBackground(new Background(new BackgroundFill(Color.rgb(100,100,100),null,null)));
        butCancel.setOnMouseClicked(e -> stage.hide());

        //创建一个新舞台对象
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);


        //创建一个场景
        Group group = new Group();
        group.getChildren().addAll(lab1,txtGroupName,labMsg,butOk,butCancel);
        Scene scene = new Scene(group, 300, 240);
        scene.setFill(Color.rgb(45, 47, 51));
        scene.setOnMousePressed(e -> {
            //记录原位置
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });
        scene.setOnMouseDragged(e -> {
            //设置新位置
            stage.setX(e.getScreenX() - mouseX);
            stage.setY(e.getScreenY() - mouseY);
        });
        //设置场景
        stage.setScene(scene);
        //显示舞台
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

