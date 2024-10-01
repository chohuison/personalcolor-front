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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyUserController implements Initializable {
    @FXML private Button modify_btn;
    @FXML public ComboBox<String> personalColorBox;
    @FXML public ComboBox<String> ageBox;
    @FXML public ComboBox<String> genderBox;
    @FXML public TextField nameField;
    @FXML public TextField idField;
    @FXML public PasswordField pwField;
    @FXML public Label information;
    @FXML private Button back_btn;

    private String name;
    private String userID;
    private String password;
    private String age;
    private String gender;
    private String seasonType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> personalColorList = FXCollections.observableArrayList("Spring","Summer","Autumn","Winter");
        ObservableList<String> ageList = FXCollections.observableArrayList("10대","20대","30대","40대");
        ObservableList<String> genderList = FXCollections.observableArrayList("여자","남자");

        personalColorBox.setItems(personalColorList);
        ageBox.setItems(ageList);
        genderBox.setItems(genderList);
    }

    public void handleButtonModifyInfo(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/PasswordCheck-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent PasswordCheck = fxmlLoader.load();

        String newName = nameField.getText();
        String newPw = pwField.getText();
        String newAge = ageBox.getSelectionModel().getSelectedItem();
        String newGender = genderBox.getSelectionModel().getSelectedItem();
        String newSeasonType = personalColorBox.getSelectionModel().getSelectedItem();

        if(newName == null)
            newName = name;

        else if(newPw == null)
            newPw = password;

        PasswordCheckController passwordCheckController = fxmlLoader.getController();
        passwordCheckController.initUserInfo("정보수정", name, userID, password, age, gender, seasonType);
        passwordCheckController.initModifyUserInfo(newName, newPw, newAge, newGender, newSeasonType);

        Scene scene = new Scene(PasswordCheck);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("PasswordCheck Page");
        stage.show();
    }

    public void handleButtonBack(ActionEvent event) throws IOException
    {
        closeStage();

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

        nameField.setText(name);
        idField.setText(userID);
        pwField.setText(password);
        ageBox.setValue(age);
        genderBox.setValue(gender);
        personalColorBox.setValue(seasonType);
    }
}
