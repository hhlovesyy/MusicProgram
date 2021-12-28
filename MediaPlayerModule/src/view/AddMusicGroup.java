package view;

import java.io.File;
import java.util.List;

import cn.itheima.media.MainApp;
import cn.itheima.utils.XMLUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*类：新建歌单*/
public class AddMusicGroup {
	private Stage parentStage;// 父窗体
	private VBox groupVBox;// 父窗体中显示歌单列表的VBox对象
	private MusicPlayerMainApp musicPlayerMainApp;

	    //移动前的x,y坐标
	    private double mouseX;
	    private double mouseY;

	    //本窗体的舞台对象
	    private Stage stage;

	    public AddMusicGroup(Stage parentStage, VBox groupVBox, MusicPlayerMainApp musicPlayerMainApp) {
	        this.parentStage = parentStage;
	        this.groupVBox = groupVBox;
	        this.musicPlayerMainApp = musicPlayerMainApp;

	        //1.新建歌单：Label
	        Label lab1 = new Label("新建歌单");
	        lab1.setTextFill(Color.WHITE);
	        lab1.setPrefWidth(150);
	        lab1.setPrefHeight(50);
	        lab1.setLayoutX(20);
	        lab1.setLayoutY(0);

	        //2.关闭按钮：ImageView
	        ImageView v1 = new ImageView("img/topandbottom/closeDark.png");
	        v1.setFitWidth(13);
	        v1.setFitHeight(13);
	        Label lab2 = new Label("", v1);
	        lab2.setMinWidth(13);
	        lab2.setMinHeight(13);
	        lab2.setPrefWidth(13);
	        lab2.setPrefHeight(13);
	        lab2.setLayoutX(270);
	        lab2.setLayoutY(20);
	        lab2.setOnMouseClicked(e ->{
	            stage.hide();
	        });

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

	        //5.取消按钮：Button
	        Button butCancel = new Button("取消");
	        butCancel.setPrefWidth(80);
	        butCancel.setPrefHeight(30);
	        butCancel.setLayoutX(150);
	        butCancel.setLayoutY(190);
	        butCancel.setTextFill(Color.WHITE);
	        butCancel.setBackground(new Background(new BackgroundFill(Color.rgb(100,100,100),null,null)));
	        butCancel.setOnMouseClicked(e -> stage.hide());


	        //6.确定按钮：Button
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
	            //2.验证新的歌单名是否重复
	            List<String> groupNameList = XMLUtils.readAllGroup();
	            for (String s : groupNameList) {
	                if (txt.equals(s)) {
	                    labMsg.setText("歌单名称：" + txt + " 已经存在！");
	                    return;
	                }
	            }
	            //3.写入MusicGroup.xml中
	            XMLUtils.addGroup(txt);

	            //4.更新主窗体上的VBox列表
	            //hBox1——(1).心形图标：ImageView
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


	            //hBox1——(2).歌单名称：Label
	            Label labGroupName = new Label(txt);
	            labGroupName.setMinHeight(0);
	            labGroupName.setPrefHeight(20);
	            labGroupName.setPrefWidth(150);
	            labGroupName.setTextFill(Color.rgb(210,210,210));
	            labGroupName.setOnMouseEntered(ee -> labGroupName.setTextFill(Color.WHITE));
	            labGroupName.setOnMouseExited(ee -> labGroupName.setTextFill(Color.rgb(210,210,210)));


	            //hBox1——(3).播放图片：ImageView
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


	            //hBox1——(4).+符号：ImageView
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
	            
	            lab3.setOnMouseClicked(ee -> {
	                //显示"打开文件对话框"
	                FileChooser fileChooser = new FileChooser();
	                fileChooser.setTitle("打开音乐文件");
	                //过滤文件
	                fileChooser.getExtensionFilters().addAll(
	                        new FileChooser.ExtensionFilter("MP3", "*.mp3"),
	                        new FileChooser.ExtensionFilter("flac","*.flac"),
	                        new FileChooser.ExtensionFilter("所有文件","*.*")
	                );
	                List<File> files = fileChooser.showOpenMultipleDialog(parentStage);
	                if (files != null && files.size() > 0) {
	                    //将集合中的每个文件的路径写入到xml文件中
	                    XMLUtils.insertSounds(labGroupName.getText().trim(),files);
	                }
	            });

	            //hBox1——(5).垃圾桶符号：ImageView
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

	        
	        //创建一个新舞台对象
	        stage = new Stage();
	        stage.initStyle(StageStyle.UNDECORATED);


	        //创建一个场景
	        Group group = new Group();
	        group.getChildren().addAll(lab1,lab2,txtGroupName,labMsg,butOk,butCancel);
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
