package TestThings;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import media.MusicLabel;
import media.PlayBean;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainAppTest1ForClickOne extends Application {

    /**
     * @note 以下字段为客户端的Id,将在登录或者是注册之后保存在本地客户端
     */
    public static int userId=-100; //初始-100,如果注册或者是进行登录这里就更新为新的id
    //public static ClientSocketUtils clientSocketUtils;// 这个是客户端的socket服务器

    public static List<MusicLabel> songlistlabels=null;
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

    //5.显示歌单列表的VBox对象
    public static VBox groupVBox;

    //6.改变窗体前，X,Y坐标
    private double xOffset;
    private double yOffset;

    //7.歌单名称标签
    private Label labGroupName;

    //8.播放列表的TableView
    private TableView<PlayBean> tableView;

    //9.当前播放歌曲的索引
    private int currentIndex;
    //10.当前播放的时间的前一秒--设置滚动条
    private int prevSecond;
    //11.当前播放的PlayBean
    private PlayBean currentPlayBean;
    //12.下侧面板的：总时间
    private Label labTotalTime;
    //13.碟片的ImageView对象
    private ImageView panImageView;
    //14.旋转的时间轴对象
    private Timeline timeline;
    //15.背景
    private ImageView backImageView;

    //16.播放按钮的ImageView对象
    private ImageView butPlayImage;

    //17.播放按钮的Label
    private Label labPlay;

    //18.当前播放模式：
    private int playMode = 1;//1 : 列表循环；2. 顺序播放  3.单曲循环

    //19.播放时间滚动条对象
    private Slider sliderSong;

    //20.已播放时间的Lable
    private Label labPlayTime;

    //21.音量滚动条
    private Slider sldVolume;

    //22.音量的进度条
    private ProgressBar volumeProgress;

    //23.记录静音前的音量
    private double prevVolumn;

    //24.显示歌词的VBox容器
    private VBox lrcVBox;
    //25.存储歌词时间的ArrayList
    private ArrayList<BigDecimal> lrcList = new ArrayList<>();
    //26.当前歌词的索引
    private int currentLrcIndex;






    @Override
    public void start(Stage primaryStage) throws Exception {
        staticStage = primaryStage;
        //clientSocketUtils=new ClientSocketUtils();
        //设置舞台
        //1.创建一个BorderPane对象
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(getLeftPane());
        borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        //2.创建一个场景
        Scene scene = new Scene(borderPane, 1210, 800);//场景宽度：1300像素；场景高度：800像素
        //3.将场景设置到舞台
        primaryStage.setScene(scene);
        //4.将舞台的标题栏去掉
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //显示舞台
        primaryStage.show();
    }


    //播放
    private void play() {
        //读取歌词
        loadLrc();
        //1.设置总时间
        this.labTotalTime.setText(this.currentPlayBean.getTime());

        //设置滚动条的总的值
        this.sliderSong.setMax(this.currentPlayBean.getTotalSeconds());
        this.sliderSong.setMajorTickUnit(1);//每次前进1格
        this.sliderSong.setValue(0);
        prevSecond = 0;

        //设置初始音量
        this.currentPlayBean.getMediaPlayer().setVolume(this.volumeProgress.getProgress());
        //2.开始播放
        new Thread(){
            @Override
            public void run() {
                currentPlayBean.getMediaPlayer().play();
            }
        }.start();

        //3.设置碟片
        if (this.currentPlayBean.getImage() != null) {
            this.panImageView.setImage(this.currentPlayBean.getImage());
        }else{
            this.panImageView.setImage(new Image("img/center/pan_default.jpg"));
        }
        //4.设置旋转
        this.timeline.stop();
        this.timeline.play();

        //5.设置背景
        WritableImage wImage = this.currentPlayBean.getImage();
        if (wImage != null) {
            //虚化
            WritableImage newWritableImage = new WritableImage(
                    (int) wImage.getWidth(),
                    (int) wImage.getHeight()
            );
            PixelReader pr = wImage.getPixelReader();
            PixelWriter pw = newWritableImage.getPixelWriter();
            for (int i = 0; i < wImage.getHeight(); i++) {
                for (int j = 0; j < wImage.getWidth(); j++) {
                    Color color = pr.getColor(i, j);
                    //四次变淡
                    for (int k = 0; k < 4; k++) {
                        color = color.darker();
                    }
                    //输出
                    pw.setColor(i,j,color);

                }

            }
            this.backImageView.setImage(newWritableImage);
        }else{
            Image img = new Image("img/center/pan_default.jpg");
            PixelReader pr = img.getPixelReader();
            WritableImage writableImage = new WritableImage(
                    (int) img.getWidth(),
                    (int) img.getHeight()
            );
            PixelWriter pw = writableImage.getPixelWriter();
            for (int i = 0; i < img.getHeight(); i++) {
                for (int j = 0; j < img.getWidth(); j++) {
                    Color color = pr.getColor(i, j);
                    //四次变淡
                    for (int k = 0; k < 4; k++) {
                        color = color.darker();
                    }
                    //输出
                    pw.setColor(i,j,color);

                }

            }
            this.backImageView.setImage(writableImage);
        }

        //设置播放按钮变为：暂停
        this.butPlayImage.setImage(new Image("img/topandbottom/PauseDark.png"));
        this.labPlay.setOnMouseEntered(e-> butPlayImage.setImage(new Image("img/topandbottom/Pause.png")));
        this.labPlay.setOnMouseExited(e-> butPlayImage.setImage(new Image("img/topandbottom/PauseDark.png")));



    }

    //加载正在播放的歌曲的lrc文件(歌词文件)
    private void loadLrc() {
        if (this.currentPlayBean == null) {
            return;
        }

        //初始化lrcVBox
        this.lrcVBox.getChildren().clear();
        this.lrcVBox.setLayoutY(50 * 2 - 10);
        this.lrcList.clear();
        this.currentLrcIndex = 0;

        //读取MP3文件
        File mp3File = new File(this.currentPlayBean.getFilePath());
        //查找歌词文件
        File lrcFile = new File(mp3File.getParent(),mp3File.getName().substring(0,mp3File.getName().indexOf(".")) + ".lrc");
        if (!lrcFile.exists()) {
            return;
        }

        //读取没一行，封装歌词Label
        try {
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(new FileInputStream(lrcFile),"GBK"));
            String row = null;
            while((row = bufIn.readLine()) != null){
                if (row.indexOf("[") == -1 || row.indexOf("]") == -1) {
                    continue;
                }
                if (row.charAt(1) < '0' || row.charAt(1) > '9') {
                    continue;
                }

                String strTime = row.substring(1,row.indexOf("]"));//00:03.29
                String strMinute = strTime.substring(0, strTime.indexOf(":"));//取出：分钟
                String strSecond = strTime.substring(strTime.indexOf(":") + 1);//取出：秒和毫秒
                //转换为int分钟
                int intMinute = Integer.parseInt(strMinute);
                //换算为总的毫秒
                BigDecimal totalMilli = new BigDecimal(intMinute * 60).add(new BigDecimal(strSecond)).multiply(new BigDecimal("1000"));
                this.lrcList.add(totalMilli);

                //创建歌词Label
                Label lab = new Label(row.trim().substring(row.indexOf("]") + 1));
                lab.setMinWidth(550);
                lab.setMinHeight(35);
                lab.setMaxHeight(35);

                lab.setPrefWidth(550);
                lab.setPrefHeight(35);
                lab.setTextFill(Color.rgb(53,53,53));
                lab.setFont(new Font("黑体",18));
                lab.setAlignment(Pos.CENTER);

                //判断是否是第一个歌词，如果是，改为30号，黄色
                if (this.lrcVBox.getChildren().size() == 0) {
                    lab.setTextFill(Color.YELLOW);
                    lab.setFont(new Font("黑体",30));
                }
                //判断是否是第二行
                if (this.lrcVBox.getChildren().size() == 1) {
                    lab.setTextFill(Color.WHITE);
                }
                //将歌词Label添加到lrcVBox中
                this.lrcVBox.getChildren().add(lab);
            }

            //最后添加一行"传智播客 成就梦想"
            if(this.currentPlayBean.getMediaPlayer().getTotalDuration().toMillis() -
                    this.lrcList.get(this.lrcList.size() - 1).doubleValue() > 0){
                Label lab = new Label("传智播客 成就梦想");
                lab.setMinWidth(550);
                lab.setMinHeight(35);
                lab.setMaxHeight(35);

                lab.setPrefWidth(550);
                lab.setPrefHeight(35);
                lab.setTextFill(Color.rgb(255,0,0));
                lab.setFont(new Font("黑体",26));
                lab.setAlignment(Pos.CENTER);
                this.lrcVBox.getChildren().add(lab);
                this.lrcList.add(new BigDecimal(this.currentPlayBean.getMediaPlayer().getTotalDuration().toMillis()));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //获取下侧面板
    private BorderPane getBottomPane() {
        //*************左侧的三个按钮***********************************//
        //1.上一首
        ImageView v1 = new ImageView("img/topandbottom/LastDark.png");
        v1.setFitHeight(40);
        v1.setFitWidth(40);
        Label lab1 = new Label("", v1);
        lab1.setOnMouseEntered(e -> v1.setImage(new Image("img/topandbottom/Last.png")));
        lab1.setOnMouseExited(e -> v1.setImage(new Image("img/topandbottom/LastDark.png")));
        lab1.setOnMouseClicked(e -> {
            if (this.currentPlayBean != null) {
                this.currentPlayBean.getMediaPlayer().stop();
            }
            //停止光盘的旋转
            this.timeline.stop();
            //让当前的索引-1
            this.currentIndex--;
            if (currentIndex < 0) {
                if (this.playMode == 1) {//列表循环
                    this.currentIndex = this.tableView.getItems().size() - 1;//定位到最后一首歌
                }else{
                    this.currentIndex = 0;
                }
            }
            //设置Table的选中
            this.tableView.getSelectionModel().select(currentIndex);
            //设置播放PlayBean对象
            this.currentPlayBean = this.tableView.getItems().get(currentIndex);
            //开始播放
            play();
        });

        //2.播放按钮
        butPlayImage = new ImageView("img/topandbottom/PlayDark.png");
        butPlayImage.setFitWidth(40);
        butPlayImage.setFitHeight(40);
        labPlay = new Label("", butPlayImage);
        labPlay.setOnMouseEntered(e -> butPlayImage.setImage(new Image("img/topandbottom/Play.png")));
        labPlay.setOnMouseExited(e -> butPlayImage.setImage(new Image("img/topandbottom/PlayDark.png")));
        labPlay.setOnMouseClicked(e -> {
            //判断如果当前正在播放，暂停
            if (this.currentPlayBean.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                //设置播放器暂停
                this.currentPlayBean.getMediaPlayer().pause();
                //设置播放按钮图标为：播放
                butPlayImage.setImage(new Image("img/topandbottom/PlayDark.png"));
                labPlay.setOnMouseEntered(ee -> butPlayImage.setImage(new Image("img/topandbottom/Play.png")));
                labPlay.setOnMouseExited(ee -> butPlayImage.setImage(new Image("img/topandbottom/PlayDark.png")));
                //暂停旋转的光盘
                this.timeline.pause();
            }else if(this.currentPlayBean.getMediaPlayer().getStatus() == MediaPlayer.Status.PAUSED){
                this.currentPlayBean.getMediaPlayer().play();
                this.timeline.play();
                butPlayImage.setImage(new Image("img/topandbottom/PauseDark.png"));
                labPlay.setOnMouseEntered(ee -> butPlayImage.setImage(new Image("img/topandbottom/Pause.png")));
                labPlay.setOnMouseExited(ee -> butPlayImage.setImage(new Image("img/topandbottom/PauseDark.png")));
            }
        });

        //3.下一首
        ImageView v3 = new ImageView("img/topandbottom/NextDark.png");
        v3.setFitWidth(40);
        v3.setFitHeight(40);
        Label lab3 = new Label("", v3);
        lab3.setOnMouseEntered(e -> v3.setImage(new Image("img/topandbottom/Next.png")));
        lab3.setOnMouseExited(e -> v3.setImage(new Image("img/topandbottom/NextDark.png")));
        lab3.setOnMouseClicked(e -> {
            if (this.currentPlayBean != null) {
                this.currentPlayBean.getMediaPlayer().stop();
            }
            //停止光盘的旋转
            this.timeline.stop();
            //让当前的索引+1
            this.currentIndex++;
            if (currentIndex >= this.tableView.getItems().size()) {
                if (this.playMode == 1) {//列表循环
                    this.currentIndex = 0;//定位到第一首歌
                }else{
                    this.currentIndex = this.tableView.getItems().size() - 1;
                }
            }
            //设置Table的选中
            this.tableView.getSelectionModel().select(currentIndex);
            //设置播放PlayBean对象
            this.currentPlayBean = this.tableView.getItems().get(currentIndex);
            //开始播放
            play();
        });

        HBox hBox1 = new HBox(30);
        hBox1.setPrefWidth(255);
        hBox1.setPadding(new Insets(5, 10, 5, 30));
        hBox1.getChildren().addAll(lab1, labPlay, lab3);

        //*************************中间滚动条部分**********************************//
        //1.已播放的时间：
        labPlayTime = new Label("00:00");
        labPlayTime.setPrefHeight(50);
        labPlayTime.setPrefWidth(40);
        labPlayTime.setTextFill(Color.WHITE);
        //2.滚动条
        sliderSong = new Slider();
        sliderSong.setMinWidth(0);
        sliderSong.setMinHeight(0);
        sliderSong.setPrefWidth(300);
        sliderSong.setPrefHeight(12);
        sliderSong.getStylesheets().add("css/TopAndBottomPage.css");


        //进度条
        ProgressBar pb1 = new ProgressBar(0);
        pb1.setProgress(0);
        pb1.setMinWidth(0);
        pb1.setMinHeight(0);

        pb1.setMaxWidth(5000);
        pb1.setPrefWidth(300);
        pb1.getStylesheets().add("css/TopAndBottomPage.css");

        //Slider值发生变化时..
        sliderSong.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //设置进度条
                if (currentPlayBean != null) {
                    pb1.setProgress((newValue.doubleValue() + 1) / currentPlayBean.getTotalSeconds());
                }
            }
        });

        //Slider的鼠标抬起事件中
        sliderSong.setOnMouseReleased(e -> {
            if (currentPlayBean != null) {
                Duration duration = new Duration(sliderSong.getValue() * 1000);
                currentPlayBean.getMediaPlayer().seek(duration);//设置新的播放时间

                //同时设置Label
                Date date = new Date();
                date.setTime((long) currentPlayBean.getMediaPlayer().getCurrentTime().toMillis());
                labPlayTime.setText(new SimpleDateFormat("mm:ss").format(date));
                //设置前一秒
                prevSecond = (int)duration.toSeconds() - 1;
            }
        });


        //使用StackPane来存储滚动条和进度条
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(pb1,sliderSong );

        //3.总时间标签
        labTotalTime = new Label("00:00");
        labTotalTime.setPrefWidth(40);
        labTotalTime.setPrefHeight(50);
        labTotalTime.setTextFill(Color.WHITE);
        labTotalTime.setAlignment(Pos.CENTER_RIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(labPlayTime);
        borderPane.setCenter(stackPane);
        borderPane.setRight(labTotalTime);
        borderPane.setPrefHeight(50);

        labPlayTime.prefHeightProperty().bind(borderPane.prefHeightProperty());
        sliderSong.prefHeightProperty().bind(borderPane.prefHeightProperty());
        labTotalTime.prefHeightProperty().bind(borderPane.prefHeightProperty());


        //************************右侧的几个组件************************************//
        //1.音量图片
        ImageView v5 = new ImageView("img/topandbottom/VolumnDark.png");
        v5.setFitWidth(17);
        v5.setFitHeight(17);
        Label lab5 = new Label("",v5);
        lab5.setOnMouseEntered(e -> v5.setImage(new Image("img/topandbottom/Volumn.png")));
        lab5.setOnMouseExited(e -> v5.setImage(new Image("img/topandbottom/VolumnDark.png")));
        lab5.setOnMouseClicked(e ->{
            if (this.currentPlayBean != null) {
                //判断当前的音量
                if (this.currentPlayBean.getMediaPlayer().getVolume() != 0) {//此时有音量
                    //将当前的音量存储起来
                    this.prevVolumn = this.currentPlayBean.getMediaPlayer().getVolume();
                    //设置为：静音
                    this.currentPlayBean.getMediaPlayer().setVolume(0);
                    //设置图片
                    v5.setImage(new Image("img/left/volumnZero_1_Dark.png"));
                    lab5.setOnMouseEntered(ee-> v5.setImage(new Image("img/left/volumnZero_1.png")));
                    lab5.setOnMouseExited(ee-> v5.setImage(new Image("img/left/volumnZero_1_Dark.png")));

                    //设置音量滚动条
                    this.sldVolume.setValue(0);
                }else{//此时是静音状态
                    //恢复原音量
                    this.currentPlayBean.getMediaPlayer().setVolume(this.prevVolumn);

                    //恢复图片
                    v5.setImage(new Image("img/topandbottom/VolumnDark.png"));
                    lab5.setOnMouseEntered(ee-> v5.setImage(new Image("img/topandbottom/Volumn.png")));
                    lab5.setOnMouseExited(ee-> v5.setImage(new Image("img/topandbottom/VolumnDark.png")));

                    //恢复音量滚动条
                    this.sldVolume.setValue(this.prevVolumn * 100);
                }
            }
        });

        //2.音量滚动条
        sldVolume = new Slider();
        sldVolume.setMax(100);
        sldVolume.setValue(50);
        sldVolume.setMajorTickUnit(1);//每前进一格，增加多少的值

        sldVolume.setMinHeight(0);
//        sldVolume.setPrefHeight(10);
        sldVolume.setPrefWidth(100);
        sldVolume.getStylesheets().add("css/TopAndBottomPage.css");

        //进度条
        volumeProgress = new ProgressBar(0);
        volumeProgress.setMinHeight(0);
        volumeProgress.setProgress(0.5);//初始在中间位置
        volumeProgress.setPrefWidth(100);
        volumeProgress.setPrefHeight(10);
        volumeProgress.prefWidthProperty().bind(sldVolume.prefWidthProperty());
        volumeProgress.getStylesheets().add("css/TopAndBottomPage.css");



        //监听进度条的值发生变化时
        sldVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //1.设置它的进度条
                volumeProgress.setProgress(sldVolume.getValue() / 100);
                //2.设置音量
                if (currentPlayBean != null) {
                    currentPlayBean.getMediaPlayer().setVolume(volumeProgress.getProgress());
                }
            }
        });
        StackPane sp2 = new StackPane();
        sp2.getChildren().addAll(volumeProgress, sldVolume);

        //3.播放模式图片
        ImageView v6 = new ImageView("img/topandbottom/RepeatDark.png");
        v6.setFitWidth(25);
        v6.setFitHeight(25);
        Label lab6 = new Label("", v6);
        lab6.setOnMouseClicked(e -> {
            //此处只处理playMode，实现，放在播放的事件中
            this.playMode++;
            if (this.playMode > 3) {
                this.playMode = 1;
            }
            switch (this.playMode) {
                case 1:
                    v6.setImage(new Image("img/topandbottom/RepeatDark.png"));
                    lab6.setOnMouseEntered(ee -> v6.setImage(new Image("img/topandbottom/Repeat.png")));
                    lab6.setOnMouseExited(ee -> v6.setImage(new Image("img/topandbottom/RepeatDark.png")));
                    break;
                case 2:
                    v6.setImage(new Image("img/topandbottom/OrderPlayDark.png"));
                    lab6.setOnMouseEntered(ee -> v6.setImage(new Image("img/topandbottom/OrderPlay.png")));
                    lab6.setOnMouseExited(ee -> v6.setImage(new Image("img/topandbottom/OrderPlayDark.png")));
                    break;
                case 3:
                    v6.setImage(new Image("img/topandbottom/RepeatInOneDark.png"));
                    lab6.setOnMouseEntered(ee -> v6.setImage(new Image("img/topandbottom/RepeatInOne.png")));
                    lab6.setOnMouseExited(ee -> v6.setImage(new Image("img/topandbottom/RepeatInOneDark.png")));
                    break;
            }

        });



        //4.歌词图片
        ImageView v7 = new ImageView("img/topandbottom/ciDark.png");
        v7.setFitWidth(25);
        v7.setFitHeight(25);
        Label lab7 = new Label("", v7);

        //5.拖拽图片
        ImageView v8 = new ImageView("img/topandbottom/right_drag.png");
        v8.setFitWidth(30);
        v8.setFitHeight(50);
        Label lab8 = new Label("", v8);
        //当鼠标按下时
        lab8.setOnMousePressed(e -> {
            //记录当前鼠标在屏幕的X,Y坐标
            xOffset = e.getScreenX();
            yOffset = e.getScreenY();
        });
        //当鼠标移动时
        lab8.setOnMouseMoved(e -> {
            if(e.getY() > 34 && e.getY() < 50 &&
                    e.getX() > 0 && e.getX() < 30){
                //改变鼠标的形状
                lab8.setCursor(Cursor.NW_RESIZE);
            }else{
                lab8.setCursor(Cursor.DEFAULT);
            }
        });
        //当鼠标拖拽时
        lab8.setOnMouseDragged(e -> {
            if (staticStage.getWidth() + (e.getScreenX() - xOffset) >= 1200) {
                staticStage.setWidth(staticStage.getWidth() + (e.getScreenX() - xOffset));
                xOffset = e.getScreenX();
            }
            if (staticStage.getHeight() + (e.getScreenY() - yOffset) >= 800) {
                staticStage.setHeight(staticStage.getHeight() + (e.getScreenY() - yOffset));
                yOffset = e.getScreenY();
            }
        });
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(0, 0, 0, 10));
        hBox.setPrefWidth(270);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(lab5,sp2,lab6,lab7,lab8);

        //**********************总的BorderPane***********************************//
        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(hBox1);
        bottomPane.setCenter(borderPane);
        bottomPane.setRight(hBox);
        return bottomPane;
    }

    //创建一个左侧面板
    private BorderPane getLeftPane() {






        /**
         * 2021.12.22 新加内容 测试返回一个用户的信息
         */
        MusicLabel user1 = new MusicLabel("测试用户:周杰伦","USER",20);
        //user1.setMinHeight(0);
        user1.init();// 这句话还是需要的,当然也可以传入参数,这里应用到了重载

        MusicLabel user2=new MusicLabel("测试歌单:七里香","SONGLIST",30);
        user2.init();

        MusicLabel user3=new MusicLabel("测试歌曲:曹操","SONG",30);
        user3.init();

        MusicLabel user4=new MusicLabel("测试注册用户","REGIST",-1); //-1表示注册事件,传递给服务器之后服务器返回用户的id和一个推荐的歌单,
        //还有用户自己的歌单
        user4.init();

        MusicLabel user5=new MusicLabel("测试登录用户","LOGIN",-2);
        user5.init();

        MusicLabel user6=new MusicLabel("测试新增歌单","CREATESONGLIST",-3);
        user6.init();

        MusicLabel user7=new MusicLabel("测试我的歌单","SHOWMYSONGLIST",userId);
        user7.init();

        MusicLabel user8=new MusicLabel("测试搜索栏","SEARCH",-5);// 搜索事件

        VBox vBox = new VBox(15);//15表示元素之间的间距
        vBox.setPrefWidth(255);
        vBox.setPrefHeight(300);
        vBox.setPadding(new Insets(5, 5, 5, 10));
        vBox.getChildren().addAll(user1.getLabel(), user2.getLabel(),user3.getLabel(),user4.getLabel(),
                user5.getLabel(),user6.getLabel(),user7.getLabel());


        //总面板
        BorderPane leftPane = new BorderPane();
        leftPane.setTop(vBox);
        leftPane.setCenter(groupVBox);
        return leftPane;
    }

    public static void main(String[] args) {
        songlistlabels=new ArrayList<MusicLabel>();
        launch(args);
    }

}



