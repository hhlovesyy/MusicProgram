package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class LeftFunList extends Pane {
	ListView<String> listView;

	public LeftFunList() {

		this.setStyle("-fx-opacity: 1; ");

		ObservableList<String> observableList=FXCollections.observableArrayList("播放界面","音乐馆","我的音乐");				
		listView=new ListView<>(observableList);
		listView.setPrefSize(230, 300);
		listView.setStyle("-fx-opacity: 0.8;-fx-background-color: transparent;");	
		listView.getStylesheets().add("css/listView.css");

		listView.setCellFactory(lv -> new ListCell<String>() { 
			private final Label label = new Label(); 
			@Override 
			protected void updateItem(String item, boolean empty) { 
				super.updateItem(item, empty); 
				if (empty) { 
					setGraphic(null); 
				} else { 
					label.setText(item); 
					
					label.setPrefWidth(listView.getWidth()-15);
					//					label.setPrefWidth(150);
					setGraphic(label); 
				} 
			} 
		}); 

//		//ListView添加item选择监听
//		listView.getSelectionModel().selectedItemProperty().addListener(new NoticeListItemChangeListener());

		//背景
		Image image = new Image("img/starry_sky.png");

		ImageView backImageView1 = new ImageView(image);
		backImageView1.setLayoutX(0);
		backImageView1.setLayoutY(0);
		backImageView1.setFitWidth(300);
		backImageView1.setFitHeight(400);
		//高斯模糊
		GaussianBlur gaussianBlur = new GaussianBlur();
		gaussianBlur.setRadius(40);
		backImageView1.setEffect(gaussianBlur);

		this.getChildren().addAll(listView);
	}


	//-----------get&set------------	
	public void setListView(ListView<String> listView) {
		this.listView = listView;
	}

	public ListView<String> getListView() {
		return listView;
	}


//	//左侧功能列表选择监听
//	private class NoticeListItemChangeListener implements ChangeListener<Object> {    	 
//		@Override
//		public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
//			if(newValue.equals("播放界面")) {
//				System.out.println("aaa");
//
//			}else if(newValue.equals("音乐馆")) {
//				System.out.println("bbb");
//
//			}else if(newValue.equals("我的音乐")) {
//				System.out.println("ccc");
//
//			}
//		}
//	}
}
