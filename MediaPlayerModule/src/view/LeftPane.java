package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LeftPane extends ScrollPane {
	private StackPane leftStackPane;
	private VBox leftVBox;
	private LeftFunList leftFunList;
	private Label labTitleUser, labUserImage, labUserID, labLogin, labQuit, labTitleFun;
	// 用户头像的ImageView对象
	private ImageView userImage;
	// 背景
	private ImageView backImageView;
	//个人信息面板
	public LeftPane() {
		leftStackPane = new StackPane();
		leftVBox = new VBox();
		leftVBox.setPrefWidth(240);
		leftVBox.setPrefHeight(600);
		leftFunList = new LeftFunList();

		// 1.title1：Label
		labTitleUser = new Label("个人基本信息");
		labTitleUser.setPrefWidth(240);
		labTitleUser.setPrefHeight(20);
		labTitleUser.setTextFill(Color.rgb(160, 160, 160));// 设置颜色

		// 用户头像：ImageView
		userImage = new ImageView("img/login_dark.png");
		userImage.setFitWidth(90);
		userImage.setPreserveRatio(true);// 根据高度，设置等比例的宽度
		// 2.用户头像：Label
		labUserImage = new Label("", userImage);
		labUserImage.setPrefWidth(100);
		labUserImage.setPrefHeight(100);
		labUserImage.setOnMouseEntered(e -> userImage.setImage(new Image("img/login_light.png")));
		labUserImage.setOnMouseExited(e -> userImage.setImage(new Image("img/login_dark.png")));
//		labUserImage.setOnMouseClicked(e -> {
//			/*			
//			点击监听事件...
//			 */
//		});

		// 3.用户昵称：Label
		labUserID = new Label("用户昵称");
		labUserID.setPrefWidth(140);
		labUserID.setPrefHeight(100);
		labUserID.setTextFill(Color.LIGHTBLUE);
		labUserID.setFont(new Font("黑体", 20));
		// 绽放效果
		Bloom bloom = new Bloom();
		bloom.setThreshold(0.5);
		labUserID.setEffect(bloom);

		// 封装2和3的控件的HBox(水平布局)
		HBox userInfoHBox = new HBox(5);
		userInfoHBox.getChildren().addAll(labUserImage, labUserID);

		// 4.登录按钮：Label
		labLogin = new Label("登录|注册");
		labLogin.setPrefWidth(190);
		labLogin.setPrefHeight(50);
		labLogin.setTextFill(Color.LIGHTGRAY);
		labLogin.setFont(new Font("黑体", 18));
		labLogin.setEffect(bloom);
		labLogin.setOnMouseClicked(e -> {
			/*
			点击监听事件...
			 */

		});

		// 5.退出按钮：Label
		labQuit = new Label("退出|切换账号");
		labQuit.setPrefWidth(190);
		labQuit.setPrefHeight(50);
		labQuit.setTextFill(Color.LIGHTGRAY);
		labQuit.setFont(new Font("黑体", 18));
		labQuit.setEffect(bloom);
//		labQuit.setOnMouseClicked(e -> {
//			/*			
//      			点击监听事件...
//			 */
//		});

		// 将1,(2,3)HBox,4,5封装到一个VBox中
		VBox userVBox = new VBox(15);// 15表示元素之间的间距
		userVBox.setPrefWidth(240);
		userVBox.setPrefHeight(350);
		userVBox.setPadding(new Insets(5, 5, 5, 10));
		userVBox.getChildren().addAll(labTitleUser, userInfoHBox, labLogin, labQuit);

		// 6.title3：Label
		labTitleFun = new Label("Java音乐播放器");
		labTitleFun.setPrefWidth(240);
		labTitleFun.setPrefHeight(20);
		labTitleFun.setTextFill(Color.rgb(160, 160, 160));
		
		//再次封装
		leftVBox.getChildren().addAll(userVBox, labTitleFun, leftFunList);
		leftVBox.setTranslateX(20);

		// 背景
		Image backImage = new Image("img/starry_sky.png");
		backImageView = new ImageView(backImage);
		backImageView.setLayoutX(0);
		backImageView.setLayoutY(0);
		backImageView.setFitWidth(260);
		backImageView.setFitHeight(300);
		// 高斯模糊
		GaussianBlur gaussianBlur = new GaussianBlur();
		gaussianBlur.setRadius(40);
		backImageView.setEffect(gaussianBlur);
		//封装
		leftStackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		leftStackPane.getChildren().addAll(backImageView, leftVBox);

		// 左侧ScrollPane
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.setPadding(new Insets(0, 0, 0, 0));
		this.setContent(leftStackPane);
		this.setPrefWidth(260);
		this.setPrefHeight(300);
		// this.setMouseTransparent(true);//使ScrollPane不接收鼠标事件

		leftStackPane.prefWidthProperty().bind(this.widthProperty());
		leftStackPane.prefHeightProperty().bind(this.heightProperty());
		backImageView.fitWidthProperty().bind(this.widthProperty());
		backImageView.fitHeightProperty().bind(this.heightProperty());
	}

	/*--------------------get&set---------------------*/
	public StackPane getLeftStackPane() {
		return leftStackPane;
	}

	public void setLeftStackPane(StackPane leftStackPane) {
		this.leftStackPane = leftStackPane;
	}

	public VBox getLeftVBox() {
		return leftVBox;
	}

	public void setLeftVBox(VBox leftVBox) {
		this.leftVBox = leftVBox;
	}

	public LeftFunList getLeftFunList() {
		return leftFunList;
	}

	public void setLeftFunList(LeftFunList leftFunList) {
		this.leftFunList = leftFunList;
	}

	public Label getLabTitleUser() {
		return labTitleUser;
	}

	public void setLabTitleUser(Label labTitleUser) {
		this.labTitleUser = labTitleUser;
	}

	public Label getLabUserImage() {
		return labUserImage;
	}

	public void setLabUserImage(Label labUserImage) {
		this.labUserImage = labUserImage;
	}

	public Label getLabUserID() {
		return labUserID;
	}

	public void setLabUserID(Label labUserID) {
		this.labUserID = labUserID;
	}

	public Label getLabLogin() {
		return labLogin;
	}

	public void setLabLogin(Label labLogin) {
		this.labLogin = labLogin;
	}

	public Label getLabQuit() {
		return labQuit;
	}

	public void setLabQuit(Label labQuit) {
		this.labQuit = labQuit;
	}

	public Label getLabTitleFun() {
		return labTitleFun;
	}

	public void setLabTitleFun(Label labTitleFun) {
		this.labTitleFun = labTitleFun;
	}

	public ImageView getBackImageView() {
		return backImageView;
	}

	public void setBackImageView(ImageView backImageView) {
		this.backImageView = backImageView;
	}

	public ImageView getUserImage() {
		return userImage;
	}

	public void setUserImage(ImageView userImage) {
		this.userImage = userImage;
	}

}
