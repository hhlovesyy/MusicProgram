package view;

import java.io.File;
import java.util.List;

import cn.itheima.media.AddGroup;
import cn.itheima.utils.XMLUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MusicPlayerMainApp extends Application {
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

	private MainPane mainPane;
	private TopPane topPane;

	@Override
	public void start(Stage primaryStage) throws Exception {

		staticStage = primaryStage;
		// 设置舞台
		// 1.创建一个BorderPane对象
		mainPane = new MainPane();
		topEvent();
		musicListEvent();
		// 2.创建一个场景
		Scene scene = new Scene(mainPane, 1250, 800);// 场景宽度：1300像素；场景高度：800像素
		// 3.将场景设置到舞台
		primaryStage.setScene(scene);
		// 4.将舞台的标题栏去掉
		primaryStage.initStyle(StageStyle.UNDECORATED);
		// 显示舞台
		primaryStage.show();

	}


	//上侧面板事件
	private void topEvent() {
		// 右侧的最小化按钮
		// 鼠标点击事件
		mainPane.getTopPane().getLab_Minmize().setOnMouseClicked(e -> staticStage.setIconified(true));// 将"舞台"最小化

		// 右侧的最大化按钮
		mainPane.getTopPane().getLabMaximize().setOnMouseClicked(e -> {
			// 如果当前窗体是正常状态，应该：最大化
			if (!staticStage.isMaximized()) {// 正常状态
				// 记录之前舞台的x,y坐标，宽度、高度的值
				resetX = staticStage.getX();
				resetY = staticStage.getY();
				resetWidth = staticStage.getWidth();
				resetHeight = staticStage.getHeight();
				// 最大化
				staticStage.setMaximized(true);

				// 设置图片
				mainPane.getTopPane().getvMaximize().setImage(new Image("img/topandbottom/resetDark.png"));
				mainPane.getTopPane().getLabMaximize().setOnMouseEntered(
						ee -> mainPane.getTopPane().getvMaximize().setImage(new Image("img/topandbottom/reset.png")));
				mainPane.getTopPane().getLabMaximize().setOnMouseExited(ee -> mainPane.getTopPane().getvMaximize()
						.setImage(new Image("img/topandbottom/resetDark.png")));

			} else {
				// 如果当前窗体是最大化状态，应该：还原
				staticStage.setX(resetX);
				staticStage.setY(resetY);
				staticStage.setWidth(resetWidth);
				staticStage.setHeight(resetHeight);
				// 设置还原状态
				staticStage.setMaximized(false);

				// 设置图片
				mainPane.getTopPane().getvMaximize().setImage(new Image("img/topandbottom/MaximizeDark.png"));
				mainPane.getTopPane().getLabMaximize().setOnMouseEntered(ee -> mainPane.getTopPane().getvMaximize()
						.setImage(new Image("img/topandbottom/MaximizeDark.png")));
				mainPane.getTopPane().getLabMaximize().setOnMouseExited(ee -> mainPane.getTopPane().getvMaximize()
						.setImage(new Image("img/topandbottom/Maximize.png")));
			}
		});

		// 右侧的关闭按钮
		mainPane.getTopPane().getLabClose().setOnMouseClicked(e -> {
			// 后期实现：记录当前正在播放的歌曲
			// 结束程序
			System.exit(0);// 结束JVM
		});

		mainPane.getTopPane().setStaticStage(staticStage);

		// 将rct的宽度绑定到stage的宽度上
		mainPane.getTopPane().getRct().widthProperty().bind(staticStage.widthProperty());

		// 当鼠标按下时
		mainPane.getTopPane().setOnMousePressed(e -> {
			// 记录鼠标相对于窗体(Scene)的X,Y坐标
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		// 当鼠标拖拽时
		mainPane.getTopPane().setOnMouseDragged(e -> {
			// 设置新的X,Y
			staticStage.setX(e.getScreenX() - mouseX);
			staticStage.setY(e.getScreenY() - mouseY);
		});
	}

	//歌单列表事件
	private void musicListEvent() {
		// 歌单+符号：ImageView
		mainPane.getMusicPane().getLab_creategroup().setOnMouseClicked(e -> {
			// 创建一个新的舞台
			new AddMusicGroup(staticStage, mainPane.getMusicPane().getGroupVBox(), this);
		});
//		// 专辑+符号：ImageView
//		mainPane.getMusicPane().getLab_createalbum().setOnMouseClicked(e -> {
//			// 创建一个新的舞台
//			new AddMusicGroup(staticStage, mainPane.getMusicPane().getAlbumVBox(), this);
//		});

		mainPane.getMusicPane().setStaticStage(staticStage);

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	

}
