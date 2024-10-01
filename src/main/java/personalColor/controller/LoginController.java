package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LoginController
{
   @FXML private Button information;
   @FXML private Button login_btn;
   @FXML private Button sign_btn;
   @FXML private TextField user_id;
   @FXML private PasswordField inputPW;

   private DataOutputStream dos;
   private DataInputStream dis;


    public void handleButtonLogin(ActionEvent event) throws IOException
    {
        String id = user_id.getText();
        String pw = inputPW.getText();

        if(id != null && pw != null)
        {
            Controller controller = new Controller();
            boolean result = controller.loginCommand(id, pw, dis, dos);

            if(result)
            {
                try
                {
                    closeStage();

                    URL location = new File("src/main/resources/personalColor/MainPage-view.fxml").toURL();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(location);

                    Parent mainPage = loader.load();

                    //user_id 전달하는 부분
                    MainPageController mainPageController = loader.getController();
                    mainPageController.initData(user_id.getText());

                    Scene scene = new Scene(mainPage);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Main Page");
                    stage.show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                information.setText("입력하신 아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        }
        else
            information.setText("모든 값을 입력해주세요.");
    }

    public void handleButtonSignUp(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/SignUp-view.fxml").toURL();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent signUpPage = loader.load();

        SignUpController signUpController = loader.getController();
        signUpController.initData();

        Scene scene = new Scene(signUpPage);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }

    public void closeStage()
    {
        Stage loginStage = (Stage) login_btn.getScene().getWindow();
        loginStage.close();
    }

    public void initData() throws IOException{
        this.dos = new DataOutputStream(MyInfo.socket.getOutputStream());
        this.dis = new DataInputStream(MyInfo.socket.getInputStream());
    }
}
