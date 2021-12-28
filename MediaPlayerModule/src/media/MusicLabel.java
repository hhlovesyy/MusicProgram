package media;

import TestThings.MainAppTest1ForClickOne;
import Utils.EventHandling;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 这个类用来表示在这个软件当中的label
 */
public class MusicLabel{
    String name;
    String labelKind;
    int labelId;
    Label label;
    public MusicLabel(String name, String labelKind, int labelId){
        this.label=new Label(name);
        this.labelId=labelId;
        this.labelKind=labelKind;

    }
    public void tempUseless(){
        MenuItem item1 = new MenuItem("MenuItem1");
        MenuItem item2 = new MenuItem("MenuItem2");
        MenuItem item3 = new MenuItem("MenuItem3");
        MenuItem item4 = new MenuItem("MenuItem4");
        //Button item1=new Button("添加至歌单...");
//        item1.setOnMenuValidation(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                System.out.println("item1.setOnMenuValidation");
//            }
//        });
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("menuItem1.setOnAction");
            }
        });

        //item1.setOnMouseClicked(e->{System.out.println("now with item1");});
        item2.setOnMenuValidation(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("item2.setOnMenuValidation");
            }
        });
        item3.setOnMenuValidation(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("item3.setOnMenuValidation");
            }
        });
        item4.setOnMenuValidation(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("item4.setOnMenuValidation");
            }
        });

        ContextMenu cm = new ContextMenu();
        cm.setStyle("-fx-font-size:25;");
        label.setContextMenu(cm);
        AnchorPane.setTopAnchor(label,30.0);
        cm.getItems().addAll(item1,item2,item3,item4);
    }
    public void init(){
        //label.setFont(new Font("黑体", 15));
        //user1.setPrefHeight(15);
        //label.setPrefWidth(255);
        //label.setTextFill(Color.rgb(210, 210, 210));
        //在这里如果非交互的再去处理...
//        if(labelKind!="NON_HCI"){
//            label.setOnMouseEntered(e -> label.setTextFill(Color.WHITE));
//            label.setOnMouseExited(e -> label.setTextFill(Color.rgb(210, 210, 210)));
//        }

        label.setOnMouseClicked(e -> {
            //new ShowUserInfo(staticStage, groupVBox, this, 20); // 姑且假设userid是20
            switch (labelKind){
                case "USER":
                    EventHandling.decideRequest("CLICK_A_USER",this);
                    break;
                case "SONGLIST":
                    EventHandling.decideRequest("CLICK_A_SONGLIST",this);
                    break;
                case "SONG":
                    EventHandling.decideRequest("CLICK_A_SONG",this);
                    tempUseless();
                    break;
                case "NON_HCI": // 该标签为不可交互标签
                    EventHandling.decideRequest("DO_NOTHING",this);
                    break;
                case "ALBUMLIST":
                    EventHandling.decideRequest("CLICK_AN_ALBUM",this);
                    break;
                case "REGIST": // 点击注册事件后会弹出与注册事件相关的窗口,并且下面会有一个button
                    new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).createRegistWindow(labelKind);
                    break;
                case "LOGIN":
                    new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).createLoginWindow(labelKind);
                    break;
                case "CREATESONGLIST":
                    new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).createGenerateWindow(labelKind);
                    break;
                case "SHOWMYSONGLIST":
                    //new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).showMySonglist();
                    EventHandling.decideRequest("SHOW_MY_SONGLIST",this);
                    break;
                case "SEARCH":
                    new ShowUserInfo(MainAppTest1ForClickOne.staticStage,MainAppTest1ForClickOne.groupVBox).createSearchWindow(labelKind);
            }

        });
    }

    //public void init()

    public String getName() {
        return name;
    }

    public int getLabelId() {
        return labelId;
    }

    public Label getLabel() {
        return label;
    }
}
