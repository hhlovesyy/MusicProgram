package view;

import cn.itheima.utils.ImageUtils;
import cn.itheima.utils.XMLUtils;
import com.sun.javafx.scene.control.skin.LabeledSkinBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import sun.text.resources.ro.CollationData_ro;



public class TopPane extends BorderPane{
	//1.全局的"舞台"对象
    public static Stage staticStage;
    //2.最大化之前的x,y坐标
    private double resetX;
    private double resetY;
    //3.最大化之前的宽度、高度
    private double resetWidth;
    private double resetHeight;
    //4.窗体移动前，相对于Scene的X,Y坐标
    private double mouseX;
    private double mouseY;
    
    private Label lab_Minmize,labMaximize,labClose;
    Rectangle rct;
    ImageView vMaximize;

	//创建上侧面板
    public TopPane() {
    	//1.左侧的Logo:HBox
        ImageView imgView = new ImageView("img/topandbottom/LOGO.png");
        imgView.setFitHeight(50);//设置图片的高度：50像素
        imgView.setPreserveRatio(true);//根据图片设置的高度，保持宽高比；

        HBox HBox_Logo = new HBox();
        HBox_Logo.setAlignment(Pos.BASELINE_LEFT);//左对齐
        HBox_Logo.setPrefWidth(600);
        HBox_Logo.setPrefHeight(50);
        HBox_Logo.setMaxHeight(50);
        HBox_Logo.setPadding(new Insets(10,0,10,15));//设置内部元素与四边的间距
        HBox_Logo.getChildren().add(imgView);
       
        //2.右侧的最小化按钮
        ImageView VMinmize = new ImageView("img/topandbottom/MinmizeDark.png");
        VMinmize.setFitWidth(15);
        VMinmize.setFitHeight(15);
        lab_Minmize = new Label("", VMinmize);
        lab_Minmize.setMinWidth(0);//设置Label的最小宽度
        lab_Minmize.setMinHeight(0);//设置Label的最小高度
        lab_Minmize.setPrefWidth(15);
        lab_Minmize.setPrefHeight(15);
        
        //鼠标移入移出事件
        lab_Minmize.setOnMouseEntered(e -> VMinmize.setImage(new Image("img/topandbottom/Minmize.png")));
        lab_Minmize.setOnMouseExited(e -> VMinmize.setImage(new Image("img/topandbottom/MinmizeDark.png")));

        //3.右侧的最大化按钮
        vMaximize = new ImageView("img/topandbottom/MaximizeDark.png");
        vMaximize.setFitWidth(15);
        vMaximize.setFitHeight(15);
        labMaximize = new Label("",vMaximize);
        labMaximize.setMinWidth(0);
        labMaximize.setMinHeight(0);
        labMaximize.setPrefWidth(15);
        labMaximize.setPrefHeight(15);
        labMaximize.setOnMouseEntered(e -> vMaximize.setImage(new Image("img/topandbottom/Maximize.png")));
        labMaximize.setOnMouseExited(e -> vMaximize.setImage(new Image("img/topandbottom/MaximizeDark.png")));

        //4.右侧的关闭按钮
        ImageView vClose = new ImageView("img/topandbottom/CloseDark.png");
        vClose.setFitWidth(15);
        vClose.setFitHeight(15);
        labClose = new Label("", vClose);
        labClose.setMinWidth(15);
        labClose.setMinHeight(15);
        labClose.setPrefWidth(15);    
        labClose.setPrefHeight(15);
        labClose.setOnMouseEntered(e -> vClose.setImage(new Image("img/topandbottom/Close.png")));
        labClose.setOnMouseExited(e -> vClose.setImage(new Image("img/topandbottom/CloseDark.png")));
  
        //组合最小化、最大化、关闭键
        HBox hBox2 = new HBox(20);//内部元素之间的间距：10像素
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setPrefWidth(150);
        hBox2.setPrefHeight(50); 
        hBox2.getChildren().addAll(lab_Minmize,labMaximize,labClose);
        hBox2.setPadding(new Insets(5,0,0,40));

        //下侧的红线
        rct = new Rectangle();
        rct.setX(0);
        rct.setY(0);
        rct.setWidth(100);
        rct.setHeight(2);
        //设置背景色--渐变
        Stop[] stops = new Stop[]{
        		new Stop(0, Color.rgb(120, 8, 14)),
                new Stop(0.5, Color.BLUE),
                new Stop(1, Color.rgb(120, 8, 14))
          };

        rct.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,stops));
        this.setLeft(HBox_Logo);
        this.setRight(hBox2);
        this.setBottom(rct);
      }

    
	//-----------get&set------------
	public static Stage getStaticStage() {
		return staticStage;
	}

	public static void setStaticStage(Stage staticStage) {
		TopPane.staticStage = staticStage;
	}

	public double getResetX() {
		return resetX;
	}

	public void setResetX(double resetX) {
		this.resetX = resetX;
	}

	public double getResetY() {
		return resetY;
	}

	public void setResetY(double resetY) {
		this.resetY = resetY;
	}

	public double getResetWidth() {
		return resetWidth;
	}

	public void setResetWidth(double resetWidth) {
		this.resetWidth = resetWidth;
	}

	public double getResetHeight() {
		return resetHeight;
	}

	public void setResetHeight(double resetHeight) {
		this.resetHeight = resetHeight;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}


	public Label getLab_Minmize() {
		return lab_Minmize;
	}


	public void setLab_Minmize(Label lab_Minmize) {
		this.lab_Minmize = lab_Minmize;
	}


	public Label getLabMaximize() {
		return labMaximize;
	}


	public void setLabMaximize(Label labMaximize) {
		this.labMaximize = labMaximize;
	}


	public Label getLabClose() {
		return labClose;
	}


	public void setLabClose(Label labClose) {
		this.labClose = labClose;
	}


	public Rectangle getRct() {
		return rct;
	}


	public void setRct(Rectangle rct) {
		this.rct = rct;
	}


	public ImageView getvMaximize() {
		return vMaximize;
	}


	public void setvMaximize(ImageView vMaximize) {
		this.vMaximize = vMaximize;
	}


}
