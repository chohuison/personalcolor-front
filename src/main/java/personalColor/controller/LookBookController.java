package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.Controller;
import personalColor.persistence.LookBook;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class LookBookController
{
    public Label information;
    @FXML private Button home_btn;
    @FXML private Button enroll_btn;

    @FXML private Button spring;
    @FXML private Button summer;
    @FXML private Button autumn;
    @FXML private Button winter;

    @FXML private AnchorPane ap;
    @FXML private VBox vbox;

    private Boolean springState;
    private Boolean summerState;
    private Boolean autumnState ;
    private Boolean winterState;
    private String userID;
    private String personalColor;
    private DataOutputStream dos;
    private DataInputStream dis;

    public void setSpringState(Boolean springState) {
        this.springState = springState;
    }

    public void setSummerState(Boolean summerState) {
        this.summerState = summerState;
    }

    public void setAutumnState(Boolean autumnState) {
        this.autumnState = autumnState;
    }

    public void setWinterState(Boolean winterState) {
        this.winterState = winterState;
    }

    public void initData() throws IOException, URISyntaxException {
        springState = true;
        summerState = true;
        autumnState = true;
        winterState = true;
        viewLookBookList();
    }

    public void handleButtonHome(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/MainPage-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);

        Parent mainPage = fxmlLoader.load();
        MainPageController mainPageController = fxmlLoader.getController();

        mainPageController.initData(userID);
        Scene scene = new Scene(mainPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Recommend Cosmetics");
        stage.show();
    }

    public void handleButtonEnroll(ActionEvent event) throws IOException
    {
        closeStage();
        URL location = new File("src/main/resources/personalColor/LookBook_Enroll-view.fxml").toURL();
        URL location_css = new File("src/main/resources/personalColor/LookBook_Enroll.css").toURL();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);

        Parent lookBookEnrollPage = fxmlLoader.load();
        LookBookEnrollController lookBookEnrollController = fxmlLoader.getController();

        lookBookEnrollController.initData(userID,personalColor);

        Scene scene = new Scene(lookBookEnrollPage);
        scene.getStylesheets().add(location_css.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Recommend Cosmetics");
        stage.show();
    }

    public void handleButtonSpring(ActionEvent event) throws IOException, URISyntaxException {
        if(springState) setSpringState(false);
        else setSpringState(true);

        if(springState)
        {
            spring.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: #4e4557; " +
                            "-fx-border-style: solid; " +
                            "-fx-border-width: 3px");
        }
        else
        {
            spring.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: none; " +
                            "-fx-border-style: none; " +
                            "-fx-border-width: 0px");
        }

        viewLookBookList();
    }

    public void handleButtonSummer(ActionEvent event) throws IOException, URISyntaxException {
        if(summerState) setSummerState(false);
        else setSummerState(true);

        if(summerState)
        {
            summer.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: #4e4557; " +
                            "-fx-border-style: solid; " +
                            "-fx-border-width: 3px");
        }
        else
        {
            summer.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: none; " +
                            "-fx-border-style: none; " +
                            "-fx-border-width: 0px");
        }

        viewLookBookList();
    }

    public void handleButtonAutumn(ActionEvent event) throws IOException, URISyntaxException {
        if(autumnState) setAutumnState(false);
        else setAutumnState(true);

        if(autumnState)
        {
            autumn.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: #4e4557; " +
                            "-fx-border-style: solid; " +
                            "-fx-border-width: 3px");
        }
        else
        {
            autumn.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: none; " +
                            "-fx-border-style: none; " +
                            "-fx-border-width: 0px");
        }

        viewLookBookList();
    }

    public void handleButtonWinter(ActionEvent event) throws IOException, URISyntaxException {
        if (winterState) setWinterState(false);
        else setWinterState(true);

        if(winterState)
        {
            winter.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: #4e4557; " +
                            "-fx-border-style: solid; " +
                            "-fx-border-width: 3px");
        }
        else
        {
            winter.setStyle
                    ("-fx-background-radius: 50%; " +
                            "-fx-border-radius: 50%; " +
                            "-fx-border-color: none; " +
                            "-fx-border-style: none; " +
                            "-fx-border-width: 0px");
        }

        viewLookBookList();
    }

    public void viewLookBookList() throws IOException, URISyntaxException {
        ap.getChildren().clear(); // ap의 모든 자식 요소를 제거
        vbox.getChildren().clear(); // vbox의 모든 자식 요소를 제거

        ap.getChildren().add(vbox);
        ap.setPrefHeight(0);

        System.out.println("1");

        System.out.println("2");

        Controller controller = new Controller();
        List<LookBook> lookBookList = controller.lookBookCommand(dis,dos);

        for(int i = lookBookList.size()-1; i >= 0; i--)
        {
            System.out.println(lookBookList.get(i).getLbExplanation());
            System.out.println(lookBookList.get(i).getLbRoot());
        }

        System.out.println("3");
        for(int i = lookBookList.size()-1; i >= 0; i--)
        {
            System.out.println("4");
            if(lookBookList.get(i).getSeasonType().equals("spring") && springState)
            {
                String img = lookBookList.get(i).getLbRoot();
                add(lookBookList.get(i).getUserID(),lookBookList.get(i).getLbExplanation(), img);
            }
            else if (lookBookList.get(i).getSeasonType().equals("summer") && summerState)
            {
                String img = lookBookList.get(i).getLbRoot();
                add(lookBookList.get(i).getUserID(),lookBookList.get(i).getLbExplanation(), img);
            }
            else if (lookBookList.get(i).getSeasonType().equals("autumn") && autumnState)
            {
                String img = lookBookList.get(i).getLbRoot();
                add(lookBookList.get(i).getUserID(),lookBookList.get(i).getLbExplanation(), img);
            }
            else if (lookBookList.get(i).getSeasonType().equals("winter") && winterState)
            {
                String img = lookBookList.get(i).getLbRoot();
                add(lookBookList.get(i).getUserID(),lookBookList.get(i).getLbExplanation(), img);
            }
        }
    }

    public void add(String userID, String label, String strImage) throws URISyntaxException, MalformedURLException {
        System.out.println(label);
        System.out.println(strImage);

        String imgPath = strImage;
        File imageFile= new File(imgPath);

        System.out.println(imageFile.getAbsolutePath());

        Image image = new Image(imageFile.getAbsolutePath());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(380);
        imageView.setFitHeight(380);
        imageView.setId("lb_Img");

        HBox hbox = new HBox();
        hbox.setStyle("-fx-padding: 10px");
        hbox.getChildren().addAll(imageView);

        Text textLabel = new Text(label);
        textLabel.setWrappingWidth(200); // 텍스트의 최대 너비 설정
        textLabel.setFont(Font.font("Arial", 18)); // 원하는 폰트 및 크기 설정
        textLabel.setFill(Color.BLACK);

        TextFlow textFlow = new TextFlow(textLabel);
        textFlow.setPrefWidth(200);

        Text textUserId = new Text(userID);
        textUserId.setWrappingWidth(200);
        textUserId.setWrappingWidth(200);
        textUserId.setFont(Font.font("Arial", 24)); // 원하는 폰트 및 크기 설정
        textUserId.setId("userId");
        textUserId.setStyle("-fx-padding: 10px;");

        TextFlow textFlow1 = new TextFlow(textUserId);
        textFlow1.setPrefWidth(200);

        VBox userBox = new VBox();
        userBox.getChildren().add(textFlow1);
        userBox.setPrefWidth(400);
        userBox.setPrefHeight(40);
        userBox.setId("userBox");

        VBox vBox = new VBox();
        vBox.setId("vBox");
        vBox.getChildren().addAll(userBox, hbox,textFlow);
        vBox.setStyle("-fx-border-style: solid; " +
                "-fx-border-width: 1px;");

        vbox.setMargin(vBox, new Insets(0,0,20,0));
        vbox.setMargin(textFlow, new Insets(10,10,10,10));
        vbox.getChildren().add(vBox);
        ap.setPrefHeight(ap.getPrefHeight() + 600);
    }

    public void closeStage()
    {
        Stage loginStage = (Stage) home_btn.getScene().getWindow();
        loginStage.close();
    }

    public void initData(String userID, String personalColor) throws IOException {
        this.userID = userID;
        this.personalColor = personalColor;
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
}
