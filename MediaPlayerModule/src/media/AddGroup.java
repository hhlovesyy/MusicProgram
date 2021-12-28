package media;

import TestThings.MainAppTest1ForClickOne;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddGroup {
    private Stage parentStage;//父窗体
    private VBox groupVBox;//父窗体中显示歌单列表的VBox对象
    private MainAppTest1ForClickOne mainApp;

    //移动前的x,y坐标
    private double mouseX;
    private double mouseY;

    //本窗体的舞台对象
    private Stage stage;

    public AddGroup(Stage parentStage, VBox groupVBox, MainAppTest1ForClickOne mainApp) {
        this.parentStage = parentStage;
        this.groupVBox = groupVBox;
        this.mainApp = mainApp;

        //1.新建歌单：Label
        Label lab1 = new Label("新建用户");
        lab1.setTextFill(Color.WHITE);
        lab1.setPrefWidth(150);
        lab1.setPrefHeight(50);
        lab1.setLayoutX(20);
        lab1.setLayoutY(0);

//        //2.关闭按钮：ImageView
//        ImageView v1 = new ImageView("img/topandbottom/closeDark.png");
//        v1.setFitWidth(13);
//        v1.setFitHeight(13);
//        Label lab2 = new Label("", v1);
//        lab2.setMinWidth(13);
//        lab2.setMinHeight(13);
//        lab2.setPrefWidth(13);
//        lab2.setPrefHeight(13);
//        lab2.setLayoutX(270);
//        lab2.setLayoutY(20);
//        lab2.setOnMouseClicked(e ->{
//            stage.hide();
//        });

        //3.文本框：TextField
        TextField txtGroupName = new TextField();
        txtGroupName.setPromptText("请输入用户名称");
        txtGroupName.setPrefWidth(220);
        txtGroupName.setPrefHeight(15);
        txtGroupName.setLayoutX(20);
        txtGroupName.setLayoutY(90);

        TextField txtGroupName1 = new TextField();
        txtGroupName1.setPromptText("请输入用户密码");
        txtGroupName1.setPrefWidth(220);
        txtGroupName1.setPrefHeight(15);
        txtGroupName1.setLayoutX(20);
        txtGroupName1.setLayoutY(110);

        TextField txtGroupName2 = new TextField();
        txtGroupName2.setPromptText("请输入邮箱地址");
        txtGroupName2.setPrefWidth(220);
        txtGroupName2.setPrefHeight(15);
        txtGroupName2.setLayoutX(20);
        txtGroupName2.setLayoutY(130);

        TextField txtGroupName3 = new TextField();
        txtGroupName3.setPromptText("请输入手机号");
        txtGroupName3.setPrefWidth(220);
        txtGroupName3.setPrefHeight(15);
        txtGroupName3.setLayoutX(20);
        txtGroupName3.setLayoutY(150);

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





            //2.歌单名称：Label
            Label labGroupName = new Label(txt);
            labGroupName.setMinHeight(0);
            labGroupName.setPrefHeight(15);
            labGroupName.setPrefWidth(150);
            labGroupName.setTextFill(Color.rgb(210,210,210));
            labGroupName.setOnMouseEntered(ee -> labGroupName.setTextFill(Color.WHITE));
            labGroupName.setOnMouseExited(ee -> labGroupName.setTextFill(Color.rgb(210,210,210)));









            HBox hBox1 = new HBox(10);
            hBox1.getChildren().addAll(labGroupName);
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
}

