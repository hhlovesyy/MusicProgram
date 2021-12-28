package media;

import TestThings.MainAppTest1ForClickOne;
import Utils.EventHandling;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * 这里面装的是labels和buttons
 */
public class MusicPanel {
    int panelId;
    int labelCnt;
    int buttonCnt;
    int textFieldCnt;
    Button[] buttons;
    TextField[] textFields;
    Label[] labels;
    String[] collectInfo;
    String requestKind;
    public MusicPanel(String requestKind,int labelCnt,int buttonCnt,int textFieldCnt,int panelId){
        this.buttonCnt=buttonCnt;
        this.labelCnt=labelCnt;
        this.textFieldCnt=textFieldCnt;
        this.requestKind=requestKind;
        this.panelId=panelId;
    }

    /**
     * 有前端之后再写的方法
     */
    public void setLayout(int m,int n,int x,int y){};
    // 假设UI全设置好了,调用init()
    public void init(){
        buttons=new Button[buttonCnt];//后续如果还有需求,这个button也可以是mybutton
        labels=new Label[labelCnt];
        textFields=new TextField[textFieldCnt];
        //以下开始布局.....布局也可以给函数

        //不同请求不同的UI布局 switch case
        //绑定监听函数
        switch (requestKind){
            case "REGIST":
                buttons[0]=new Button("确定");
                // 随便写一点前端的UI设计
                buttons[0].setTextFill(Color.YELLOW);
                buttons[0].setPrefWidth(80);
                buttons[0].setPrefHeight(10);
                buttons[0].setLayoutX(100);
                buttons[0].setLayoutY(300);

                // 4个label布局
                labels[0]=new Label("用户名");
                labels[0].setTextFill(Color.WHITE);
                labels[0].setPrefWidth(80);
                labels[0].setPrefHeight(10);
                labels[0].setLayoutX(100);
                labels[0].setLayoutY(30);

                labels[1]=new Label("密码");
                labels[1].setTextFill(Color.WHITE);
                labels[1].setPrefWidth(80);
                labels[1].setPrefHeight(10);
                labels[1].setLayoutX(100);
                labels[1].setLayoutY(60);

                labels[2]=new Label("邮箱");
                labels[2].setTextFill(Color.WHITE);
                labels[2].setPrefWidth(80);
                labels[2].setPrefHeight(10);
                labels[2].setLayoutX(100);
                labels[2].setLayoutY(90);

                labels[3]=new Label("电话号码");
                labels[3].setTextFill(Color.WHITE);
                labels[3].setPrefWidth(80);
                labels[3].setPrefHeight(10);
                labels[3].setLayoutX(100);
                labels[3].setLayoutY(120);

                for(int i=0;i<=3;i++){
                    textFields[i]=new TextField();
                    textFields[i].setPrefWidth(80);
                    textFields[i].setPrefHeight(10);
                    textFields[i].setLayoutX(300);
                    textFields[i].setLayoutY(30+30*i);
                }
                buttons[1]=new Button();
                buttons[0].setOnMouseClicked(e->{
                    collectInfo=new String[textFieldCnt];
                    for(int i=0;i<textFieldCnt;i++){
                        collectInfo[i]=textFields[i].getText();
                    }
                    EventHandling.giveOutRequest(requestKind,this);
                });
                break;
            case "LOGIN":
                buttons[0]=new Button("确定");
                // 随便写一点前端的UI设计
                buttons[0].setTextFill(Color.YELLOW);
                buttons[0].setPrefWidth(80);
                buttons[0].setPrefHeight(10);
                buttons[0].setLayoutX(100);
                buttons[0].setLayoutY(300);

                buttons[1]=new Button();

                // 2个label布局
                labels[0]=new Label("用户ID");
                labels[0].setTextFill(Color.WHITE);
                labels[0].setPrefWidth(80);
                labels[0].setPrefHeight(10);
                labels[0].setLayoutX(100);
                labels[0].setLayoutY(30);

                labels[1]=new Label("密码");
                labels[1].setTextFill(Color.WHITE);
                labels[1].setPrefWidth(80);
                labels[1].setPrefHeight(10);
                labels[1].setLayoutX(100);
                labels[1].setLayoutY(60);
                for(int i=0;i<=1;i++){
                    textFields[i]=new TextField();
                    textFields[i].setPrefWidth(80);
                    textFields[i].setPrefHeight(10);
                    textFields[i].setLayoutX(300);
                    textFields[i].setLayoutY(30+30*i);
                }
                buttons[0].setOnMouseClicked(e->{
                    collectInfo=new String[textFieldCnt];
                    for(int i=0;i<textFieldCnt;i++){
                        collectInfo[i]=textFields[i].getText();
                    }
                    MainAppTest1ForClickOne.userId=Integer.parseInt(textFields[0].getText());
                    EventHandling.giveOutRequest(requestKind,this);
                });

                break;
            case "CREATE_A_SONGLIST":
                buttons[0]=new Button("确定");
                buttons[1]=new Button();
                buttons[0].setTextFill(Color.YELLOW);
                buttons[0].setPrefWidth(80);
                buttons[0].setPrefHeight(10);
                buttons[0].setLayoutX(100);
                buttons[0].setLayoutY(300);

                labels[0]=new Label("新建歌单名");
                labels[0].setTextFill(Color.WHITE);
                labels[0].setPrefWidth(80);
                labels[0].setPrefHeight(10);
                labels[0].setLayoutX(100);
                labels[0].setLayoutY(30);

                textFields[0]=new TextField();
                textFields[0].setPrefWidth(80);
                textFields[0].setPrefHeight(10);
                textFields[0].setLayoutX(300);
                textFields[0].setLayoutY(30);

                buttons[0].setOnMouseClicked(e->{
                    collectInfo=new String[textFieldCnt+1];
                    collectInfo[0]= String.valueOf(MainAppTest1ForClickOne.userId);
                    System.out.println("collectInfo[0]="+collectInfo[0]);
                    collectInfo[1]=textFields[0].getText();
                    EventHandling.giveOutRequest(requestKind,this);
                });
                break;

            case "SEARCH":
                buttons[0]=new Button("搜索");
                buttons[1]=new Button();
                buttons[0].setTextFill(Color.YELLOW);
                buttons[0].setPrefWidth(80);
                buttons[0].setPrefHeight(10);
                buttons[0].setLayoutX(100);
                buttons[0].setLayoutY(300);

                labels[0]=new Label("搜索内容:");
                labels[0].setTextFill(Color.WHITE);
                labels[0].setPrefWidth(80);
                labels[0].setPrefHeight(10);
                labels[0].setLayoutX(100);
                labels[0].setLayoutY(30);

                textFields[0]=new TextField();
                textFields[0].setPrefWidth(80);
                textFields[0].setPrefHeight(10);
                textFields[0].setLayoutX(300);
                textFields[0].setLayoutY(30);

                buttons[0].setOnMouseClicked(e->{
                    collectInfo=new String[1];
                    collectInfo[0]=textFields[0].getText();
                    System.out.println("collectInfo[0]="+collectInfo[0]);
                    EventHandling.giveOutRequest(requestKind,this);
                });



        }

    }

    public String[] getCollectInfo() {
        return collectInfo;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Label[] getLabels() {
        return labels;
    }

    public TextField[] getTextFields() {
        return textFields;
    }
    /**
     * 收集get到的信息
     */

}
