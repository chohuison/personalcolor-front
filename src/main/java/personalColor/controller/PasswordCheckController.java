package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.Controller;
import personalColor.persistence.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PasswordCheckController {
    @FXML private Button back_btn;
    @FXML public Text userName;
    @FXML public TextField pwField;
    @FXML public Label pwCheckLabel;
    private String function;
    private String name;
    private String userID;
    private String password;
    private String age;
    private String gender;
    private String seasonType;
    private String newName;
    private String newPassword;
    private String newAge;
    private String newGender;
    private String newSeasonType;

    private DataOutputStream dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    private DataInputStream dis = new DataInputStream(MyInfo.socket.getInputStream());

    public PasswordCheckController() throws IOException {
    }

    public void handleButtonFunctions(ActionEvent event) throws IOException
    {
        if(pwField.getText().equals(password))
        {
            if(function.equals("정보수정"))
                handleButtonModifyInfo(event);

            else if(function.equals("회원삭제"))
                handleButtonUserDelete(event);
        }

        else
        {
            pwCheckLabel.setText("비밀번호가 일치하지 않습니다.");
        }
    }

    public void handleButtonModifyInfo(ActionEvent event) throws IOException
    {
        User user = new User(userID, newPassword, newName, newAge, newGender, newSeasonType);
        Controller controller = new Controller();
        int result = controller.modifyInfoCommand(user, dis, dos);

        closeStage();

        URL location = new File("src/main/resources/personalColor/MyPage-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent MyPage = fxmlLoader.load();

        MyPageController myPageController = fxmlLoader.getController();
        myPageController.initData(newName, userID, newPassword, newAge, newGender, newSeasonType);

        Scene scene = new Scene(MyPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("My Page");
        stage.show();
    }

    public void handleButtonUserDelete(ActionEvent event) throws IOException
    {
        Controller controller = new Controller();
        controller.deleteUserCommand(userID, dis, dos);

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

    public void handleButtonBack(ActionEvent event) throws IOException
    {
        closeStage();

        if(function.equals("정보수정"))
        {
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


        else if(function.equals("회원삭제"))
        {
            URL location = new File("src/main/resources/personalColor/MyPage-view.fxml").toURL();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            Parent MyPage = fxmlLoader.load();

            MyPageController myPageController = fxmlLoader.getController();
            myPageController.initData(name, userID, password, age, gender, seasonType);

            Scene scene = new Scene(MyPage);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("My Page");
            stage.show();
        }
    }

    public void closeStage()
    {
        Stage colorStage = (Stage) back_btn.getScene().getWindow();
        colorStage.close();
    }

    public void initUserInfo(String function, String name, String userID, String password, String age, String gender, String seasonType)  throws IOException
    {
        this.function = function;
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.seasonType = seasonType;

        userName.setText(name + "님");
    }

    public void initModifyUserInfo(String newName, String newPassword, String newAge, String newGender, String newSeasonType)  throws IOException
    {
        this.newName = newName;
        this.newPassword = newPassword;
        this.newAge= newAge;
        this.newGender = newGender;
        this.newSeasonType = newSeasonType;
    }
}
