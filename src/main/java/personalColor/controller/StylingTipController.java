package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.Controller;
import personalColor.persistence.Tip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class StylingTipController {
    @FXML private Button back_btn;
    @FXML private Button hair_tip;
    @FXML private Button fashion_tip;

    private String personalColor;
    private String userID;

    private DataOutputStream dos;
    private DataInputStream dis;

    public void handleButtonHair(ActionEvent event) throws  IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/StylingTip_Content-view.fxml").toURL();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent StylingTipPage = fxmlLoader.load();
        StylingTipContentController stylingTipContentController = fxmlLoader.getController();

        stylingTipContentController.setPersonalColor(personalColor);
        Controller controller = new Controller();
        List<Tip> tips = controller.tipCommand(personalColor,"hair", dis, dos);
        Random random = new Random();
        int num= random.nextInt(tips.size()-1);
        stylingTipContentController.setTip(tips.get(num).getDetail());
        stylingTipContentController.initData(userID);
        stylingTipContentController.setState("Hair");

        Scene scene = new Scene(StylingTipPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Styling Tips");
        stage.show();
    }

    public void handleButtonFashion(ActionEvent event) throws  IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/StylingTip_Content-view.fxml").toURL();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);

        Parent StylingTipPage = fxmlLoader.load();
        StylingTipContentController stylingTipContentController = fxmlLoader.getController();
        stylingTipContentController.setState("Fashion");

        stylingTipContentController.setPersonalColor(personalColor);
        Controller controller = new Controller();

        List<Tip> tips = controller.tipCommand(personalColor,"fashion",dis,dos);
        Random random = new Random();
        int num= random.nextInt(tips.size()-1);
        String tip=tips.get(num).getDetail();

        stylingTipContentController.setTip(tip);
        stylingTipContentController.initData(userID);
        Scene scene = new Scene(StylingTipPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Styling Tips");
        stage.show();
    }

    public void handleButtonBack(ActionEvent event) throws IOException
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
        stage.setTitle("Main Page");
        stage.show();
    }

    public void closeStage()
    {
        Stage stylingTipStage = (Stage) back_btn.getScene().getWindow();
        stylingTipStage.close();
    }

    public void initData(String userID)  throws IOException
    {
        this.userID = userID;
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
    public void setPersonalColor(String personalColor){
        this.personalColor=personalColor;
    }
}
