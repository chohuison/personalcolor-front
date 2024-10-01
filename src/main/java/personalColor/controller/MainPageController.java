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
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.control.Controller;
import personalColor.persistence.Accessory;
import personalColor.persistence.Colors;
import personalColor.persistence.Cosmetic;
import personalColor.persistence.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageController implements Initializable
{
    @FXML private Button color_btn;
    @FXML private Button cosmetic_btn;
    @FXML private Button accessory_btn;
    @FXML private Button stylingTip_btn;
    @FXML private Button lookBook_btn;
    @FXML private ComboBox personalColorBox;
    @FXML private Label userName;

    private DataOutputStream dos;
    private DataInputStream dis;
    private String userID;

    ObservableList<String> personalColorList = FXCollections.observableArrayList("spring","summer","autumn","winter");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalColorBox.setItems(personalColorList);
    }

    public void handleButtonColor(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/Color-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);

        Parent ColorPage = fxmlLoader.load();

        String seasonType = personalColorBox.getSelectionModel().getSelectedItem().toString();
        ColorController colorController = fxmlLoader.getController();
        colorController.initData(userID, seasonType);

        Controller controller = new Controller();
        List<Colors> colorList = controller.colorCommand(seasonType, dis, dos);

        for(int i = 0; i < colorList.size(); i++)
            colorController.showColor(colorList.get(i));

        Scene scene = new Scene(ColorPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Color Palette");
        stage.show();
    }

    public void handleButtonCosmetics(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/Cosmetics-view.fxml").toURL();
        URL location_css = new File("src/main/resources/personalColor/Cosmetics.css").toURL();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent cosmeticsPage = fxmlLoader.load();

        String seasonType = personalColorBox.getSelectionModel().getSelectedItem().toString();
        Controller controller = new Controller();
        List<Cosmetic> cosmeticList = controller.cosmeticCommand(seasonType, dis, dos);
        System.out.println(seasonType);
        System.out.println(cosmeticList.get(0).getProductName());

        CosmeticsController cosmeticsController = fxmlLoader.getController();
        cosmeticsController.initData(userID, seasonType);
        cosmeticsController.addCosmeticList(cosmeticList);

        Scene scene = new Scene(cosmeticsPage);

        scene.getStylesheets().add(location_css.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Recommend Cosmetics");
        stage.show();
    }

    public void handleButtonStyling(ActionEvent event) throws IOException
    {

        URL location = new File("src/main/resources/personalColor/StylingTip-view.fxml").toURL();
        URL cssLocation = new File("src/main/resources/personalColor/StylingTip.css").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        closeStage();
        Parent StylingPage = fxmlLoader.load();

        StylingTipController stylingTipController = fxmlLoader.getController();
        stylingTipController.initData(userID);
        String personalColor = personalColorBox.getSelectionModel().getSelectedItem().toString();
        stylingTipController.setPersonalColor(personalColor);

        Scene scene = new Scene(StylingPage);
        scene.getStylesheets().add(cssLocation.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Styling Tips");
        stage.show();
    }

    public void handleButtonAccessory(ActionEvent event) throws IOException, URISyntaxException {

        URL location = new File("src/main/resources/personalColor/Accessory-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        closeStage();

        Parent AccessoryPage = fxmlLoader.load();
        AccessoryController accessoryController = fxmlLoader.getController();
        Controller controller = new Controller();

        String personalColor = personalColorBox.getSelectionModel().getSelectedItem().toString();
        List<Accessory> accessoryList = controller.accessoryCommand(personalColor,dis,dos);

        String seasonType = personalColorBox.getSelectionModel().getSelectedItem().toString();
        accessoryController.initData(userID, seasonType);

        for(int i=0;i< accessoryList.size();i++)
        {  String[] img= accessoryList.get(i).getAccRoot().split(" ");
            accessoryController.add(accessoryList.get(i).getAccExplanation(), img[0], img[1]);
        }

        Scene scene = new Scene(AccessoryPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Accessory");
        stage.show();
    }

    public void handleButtonMyPage(ActionEvent event) throws IOException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/MyPage-view.fxml").toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        Parent MyPage = fxmlLoader.load();

        Controller controller = new Controller();
        User userInfo = controller.myPageInfoCommand(userID ,dis, dos).get(0);
        System.out.println(userInfo.getName() + "  " + userInfo.getGender());

        MyPageController myPageController = fxmlLoader.getController();
        myPageController.initData(userInfo.getName(), userID, userInfo.getPW(), userInfo.getAge(), userInfo.getGender(), userInfo.getSeasonType());

        Scene scene = new Scene(MyPage);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("My Page");
        stage.show();
    }

    public void handleButtonLookBook(ActionEvent event) throws IOException, URISyntaxException
    {
        closeStage();

        URL location = new File("src/main/resources/personalColor/LookBook-view.fxml").toURL();
        URL location_css = new File("src/main/resources/personalColor/LookBook.css").toURL();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);

        Parent lookBook = loader.load();

        String seasonType = personalColorBox.getSelectionModel().getSelectedItem().toString().toLowerCase();

        LookBookController lookBookController = loader.getController();
        lookBookController.initData(userID,seasonType);
        lookBookController.initData();

        Scene scene = new Scene(lookBook);
        scene.getStylesheets().add(location_css.toString());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Look Book");
        stage.show();
    }

    public void closeStage()
    {
        Stage mainStage = (Stage) lookBook_btn.getScene().getWindow();
        mainStage.close();
    }

    //user_id 받는 부분, 여기에서 이거 이용해서 user name이랑 personal color 찾으면 될거 같아욤
    public void initData(String userId) throws IOException{
        this.userID = userId;
        System.out.println(userID);

        this.dis = new DataInputStream(MyInfo.socket.getInputStream());
        this.dos = new DataOutputStream(MyInfo.socket.getOutputStream());
        //데베에 있는 유저 정보 가져오기
        String userName = userID; //데베의 유저 정보에서 이름 값 저장 및 라벨 변경
        this.userName.setText(userName);

        Controller controller = new Controller();
        String season = controller.personalColorCommand(userId ,dis, dos);

        String userPersonalColor = season;
        personalColorBox.setValue(userPersonalColor); //콤보박스 값 설정해주기
    }

}
