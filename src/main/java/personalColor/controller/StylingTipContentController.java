package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
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

public class StylingTipContentController{
    @FXML
    private Button back_btn;
    @FXML
    private Text title;
    @FXML
    private Text text;

    private String state;
    private String userID;
    private String personalColor;
    private String tip;
    private DataOutputStream dos;
    private DataInputStream dis;


    public void handleButtonFashion(ActionEvent event) throws  IOException
    {
        Controller controller = new Controller();
        List<Tip> tips = controller.tipCommand(personalColor,"fashion",dis,dos);
        Random random = new Random();
        int num= random.nextInt(tips.size()-1);
        String tip=tips.get(num).getDetail();

        setTip(tip);
        setState("Fashion");
    }

    public void handleButtonHair(ActionEvent event) throws  IOException
    { Controller controller = new Controller();
        List<Tip> tips = controller.tipCommand(personalColor,"hair",dis,dos);
        Random random = new Random();
        int num= random.nextInt(tips.size()-1);
        String tip=tips.get(num).getDetail();

        setTip(tip);
        setState("Hair");
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

    public void setState(String state)
    {
        this.state = state;

        if(state.equals("Hair"))
        {
            title.setText("Hair");
        }
        else if(state.equals("Fashion"))
        {
            title.setText("Fashion");
        }
        else
        {
            title.setText("Other things");
        }
    }

    public void closeStage()
    {
        Stage contentStage = (Stage) back_btn.getScene().getWindow();
        contentStage.close();
    }

    public void initData(String userID) throws IOException {
        this.userID = userID;
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
    public void setPersonalColor(String personalColor){
        this.personalColor=personalColor;
    }
    public void setTip(String tip){
        text.setText(tip);
    }


}
