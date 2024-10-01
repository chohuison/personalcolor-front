package personalColor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import personalColor.MainApplication;
import personalColor.MyInfo;
import personalColor.control.Controller;
import personalColor.persistence.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable
{
    @FXML private Button sign_btn;
    @FXML public ComboBox<String> personalColorBox;
    @FXML public ComboBox<String> ageBox;
    @FXML public ComboBox<String> genderBox;
    @FXML public TextField nameField;
    @FXML public TextField idField;
    @FXML public PasswordField pwField;
    @FXML public Label information;

    private DataInputStream dis;
    private DataOutputStream dos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> personalColorList = FXCollections.observableArrayList("Spring","Summer","Autumn","Winter");
        ObservableList<String> ageList = FXCollections.observableArrayList("10대","20대","30대","40대");
        ObservableList<String> genderList = FXCollections.observableArrayList("여자","남자");

        personalColorBox.setItems(personalColorList);
        ageBox.setItems(ageList);
        genderBox.setItems(genderList);
    }

    public void handleButtonSignUp(ActionEvent event) throws IOException
    {
        String userName = nameField.getText();
        String userId = idField.getText();
        String userPw = pwField.getText();
        String userAge = ageBox.getSelectionModel().getSelectedItem();
        String userGender = genderBox.getSelectionModel().getSelectedItem();
        String userPersonalColor = personalColorBox.getSelectionModel().getSelectedItem();

        User user = new User();

        if(userName != null && userId != null && userPw != null && userAge != null && userGender != null && userPersonalColor != null)
        {
            user = makeUser(userName, userId, userPw, userAge, userGender, userPersonalColor);

            Controller controller = new Controller();
            int signUpCheck = controller.signUpCommand(user,dis, dos);

            System.out.println(signUpCheck);
            if(signUpCheck == 1)
            {
                closeStage();

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login-view.fxml"));
                Parent root = fxmlLoader.load();

                LoginController loginController = fxmlLoader.getController();
                loginController.initData();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Login page");
                stage.setScene(scene);
                stage.show();
            }
            else if(signUpCheck == 0)
            {
                information.setText("이미 존재하는 아이디입니다.");
            }
        }
        else
        {
            information.setText("모든 값을 입력하거나 선택해주세요.");
        }
    }

    public void handleButtonLogin(ActionEvent event) throws IOException
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

    public void handleButtonQuestionMark(ActionEvent event) throws IOException
    {
        URL location = new File("src/main/resources/personalColor/SelfDiagnosisView.fxml").toURL();
        Parent LoginPage = FXMLLoader.load(location);

        Scene scene = new Scene(LoginPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Self-Diagnose");
        stage.show();
    }

    public void closeStage()
    {
        Stage loginStage = (Stage) sign_btn.getScene().getWindow();
        loginStage.close();
    }

    public User makeUser(String name, String id, String pw, String age, String gender, String season)
    {
        User user = new User(id, pw, name, age, gender, season);
        return user;
    }

    public void initData() throws IOException
    {
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
}