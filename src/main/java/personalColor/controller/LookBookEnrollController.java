package personalColor.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.ActionController;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class LookBookEnrollController implements Initializable {
    @FXML
    private ImageView imgView;
    @FXML
    private Button back_btn;
    @FXML
    private Button addImage;
    @FXML
    private Button check_btn;
    @FXML
    private Label user_id;
    @FXML
    private TextArea enrollText;
    @FXML
    private ComboBox<String> scope;
    private String newFilePath;
    private DataOutputStream dos;
    private DataInputStream dis;
    private String personalColor;
    private String userid;
    ObservableList<String> scopeList = FXCollections.observableArrayList("비공개","공개");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scope.setItems(scopeList);
    }

    public void handleButtonFileChoose(ActionEvent event) {
        fileChoose();
    }

    public void handleButtonBack(ActionEvent event) throws IOException, URISyntaxException {
        closeStage();

        URL location = new File("src/main/resources/personalColor/LookBook-view.fxml").toURL();
        URL location_css = new File("src/main/resources/personalColor/LookBook.css").toURL();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent lookBook = loader.load();

        LookBookController lookBookController = loader.getController();
        lookBookController.initData(user_id.getText(), personalColor);
        lookBookController.initData();

        Scene scene = new Scene(lookBook);

        scene.getStylesheets().add(location_css.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Look Book");
        stage.show();
    }

    private void fileChoose() {
        FileChooser fc = new FileChooser();
        fc.setTitle("이미지 선택");
        fc.setInitialDirectory(new File("C:/"));
        FileChooser.ExtensionFilter imgType = new FileChooser.ExtensionFilter("image file", "*.jpg", "*.gif", "*.png");
        FileChooser.ExtensionFilter txtType = new FileChooser.ExtensionFilter("text file", "*.txt", "*.doc");
        fc.getExtensionFilters().addAll(imgType, txtType);

        File selectedFile = fc.showOpenDialog(null);
        String filePath = selectedFile.getAbsolutePath();
        File file = new File(filePath);
        String fileName = file.getName();
        System.out.println("파일 이름: " + fileName);

        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            // 이미지 생성하기
            Image img = new Image(bis);
            // 이미지 띄우기
            imgView.setImage(img);

            String savePath = "img/fileChooser/" + fileName;
            saveFile(selectedFile, savePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(File sourceFile, String savePath) {
        try {
            File destinationFile = new File(savePath);
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved: " + savePath);
            newFilePath=savePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void check() throws IOException, URISyntaxException {
        String text = enrollText.getText();
        System.out.println(text);
        ActionController actionController = new ActionController();
        String selectedScope = (String) scope.getValue();
        int is=0;
        if(selectedScope=="비공개")
            is=0;
        else{
            is=1;
        }
        ActionController controller = new ActionController();
        controller.enroll(newFilePath,text,personalColor,userid,is,dos);
        closeStage();

        URL location = new File("src/main/resources/personalColor/LookBook-view.fxml").toURL();
        URL location_css = new File("src/main/resources/personalColor/LookBook.css").toURL();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent lookBook = loader.load();

        LookBookController lookBookController = loader.getController();
        lookBookController.initData(user_id.getText(), personalColor);
        lookBookController.initData();

        Scene scene = new Scene(lookBook);

        scene.getStylesheets().add(location_css.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Look Book");
        stage.show();

    }

    public void closeStage() {
        Stage lookBookEnrollStage = (Stage) back_btn.getScene().getWindow();
        lookBookEnrollStage.close();
    }

    public void initData(String userID,String seasonType) throws IOException {
        userid=userID;
        user_id.setText(userID);
        personalColor = seasonType;
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
}