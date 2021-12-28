package view;

import java.io.File;
import java.util.List;

import cn.itheima.media.PlayBean;
import cn.itheima.utils.XMLUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/*类：主面板——*/
public class MainPane extends BorderPane {
	//左侧面板
	private LeftPane leftPane;
	//上侧面板
	private TopPane topPane;
	//我的音乐
	private MusicPane musicPane;

	public MainPane() {
		leftPane = new LeftPane();
		topPane = new TopPane();
		musicPane = new MusicPane();

		this.setTop(topPane);
		this.setLeft(leftPane);
		this.setBottom(musicPane.getPlay());
		this.setCenter(musicPane.getUserAlist());
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		// **************************************监听事件********************************
		// 左侧面板
		// 1.用户头像：Label
		leftPane.getLabUserImage().setOnMouseClicked(e -> {
			/*
			 * 点击监听事件...
			 */
		});

		// 2.登录按钮：Label
		leftPane.getLabLogin().setOnMouseClicked(e -> {
			/*
			 * 点击监听事件...
			 */
		});

		// 3.退出按钮：Label
		leftPane.getLabQuit().setOnMouseClicked(e -> {
			/*
			 * 点击监听事件...
			 */
		});

		// 4.ListView添加item选择监听
		leftPane.getLeftFunList().getListView().getSelectionModel().selectedItemProperty()
				.addListener(new NoticeListItemChangeListener());

			
	}

	// 1.选择左侧列表，切换中间面板
	private void setCenterPane(int num) {
		if (num == 1) {
			this.getChildren().remove(this.getCenter());
			this.setCenter(musicPane.getLrc());
		} else if (num == 2) {
			this.getChildren().remove(this.getCenter());
			musicPane.getUserAlist().setTop(musicPane.getMusicHallPane());
			musicPane.getLabList().setText("推荐歌单");
			this.setCenter(musicPane.getUserAlist());
		} else if (num == 3) {
			this.getChildren().remove(this.getCenter());
			musicPane.getUserAlist().setTop(musicPane.getUser());
			musicPane.getLabList().setText("我的歌单");
			this.setCenter(musicPane.getUserAlist());
		}
	}

	// 2.左侧功能列表选择监听
	private class NoticeListItemChangeListener implements ChangeListener<Object> {
		@Override
		public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
			if (newValue.equals("播放界面")) {
				//测试
				System.out.println("aaa");
				setCenterPane(1);
			} else if (newValue.equals("音乐馆")) {
				//测试
				System.out.println("bbb");
				setCenterPane(2);
			} else if (newValue.equals("我的音乐")) {
				//测试
				System.out.println("ccc");
				setCenterPane(3);
			}
		}
	}

	public TopPane getTopPane() {
		return topPane;
	}

	public void setTopPane(TopPane topPane) {
		this.topPane = topPane;
	}

	public LeftPane getLeftPane() {
		return leftPane;
	}

	public void setLeftPane(LeftPane leftPane) {
		this.leftPane = leftPane;
	}

	public MusicPane getMusicPane() {
		return musicPane;
	}

	public void setMusicPane(MusicPane musicPane) {
		this.musicPane = musicPane;
	}

}
