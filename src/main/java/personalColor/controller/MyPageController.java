package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyPageController {
    @FXML private Text userName;
    @FXML private Button back_btn;
    @FXML private Button modify_btn;
    @FXML private Button logout_btn;
    @FXML private Button withdraw_btn;

    private String name;
    private String userID;
    private String password;
    private String age;
    private String gender;
    private String seasonType;

    public void handleButtonModifyInfo(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/ModifyUser.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent ModifyInfoPage = fxmlLoader.load();

        ModifyUserController modifyUserController = fxmlLoader.getController();
        modifyUserController.initData(name, userID, password, age, gender, seasonType);

        Scene scene = new Scene(ModifyInfoPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ModifyInfo Page");
        stage.show();
    }

    public void handleButtonLogOut(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/Login-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);

        Parent LoginPage = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();

        loginController.initData();

        Scene scene = new Scene(LoginPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();

    }

    public void handleButtonDeleteUser(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/PasswordCheck-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent PasswordCheck = fxmlLoader.load();

        PasswordCheckController passwordCheckController = fxmlLoader.getController();
        passwordCheckController.initUserInfo("회원삭제", name, userID, password, age, gender, seasonType);

        Scene scene = new Scene(PasswordCheck);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("PasswordCheck Page");
        stage.show();
    }

    public void handleButtonBack(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/MainPage-view.fxml").toURL();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent mainPage = loader.load();
        MainPageController mpc = loader.getController();

        mpc.initData(userID);

        Scene scene = new Scene(mainPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Main Page");
        stage.show();
    }

    public void closeStage()
    {
        Stage colorStage = (Stage) back_btn.getScene().getWindow();
        colorStage.close();
    }

    public void initData(String name, String userID, String password, String age, String gender, String seasonType)  throws IOException
    {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.seasonType = seasonType;

        userName.setText(name + "님");
    }

}
