package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import personalColor.MyInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AccessoryController {
    @FXML private Button back_btn;
    @FXML private VBox vbox;
    @FXML private Text season;


    String userID;
    private DataOutputStream dos;
    private DataInputStream dis;

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
        Stage accessoryStage = (Stage) back_btn.getScene().getWindow();
        accessoryStage.close();
    }

    public void initData(String userID, String seasonType)  throws IOException
    {
        this.userID = userID;
        season.setText(seasonType);
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }

    public void add(String label, String strImage1, String strImage2) throws URISyntaxException {
        URI imageURL1 = new URI(strImage1);
        URI imageURL2 = new URI(strImage2);
        Image image1 = new Image(imageURL1.toString());
        Image image2 = new Image(imageURL2.toString());
        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        imageView1.setFitWidth(150);
        imageView1.setFitHeight(150);
        imageView2.setFitWidth(150);
        imageView2.setFitHeight(150);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView1, imageView2);
        Text textLabel = new Text(label);
        textLabel.setWrappingWidth(200); // 텍스트의 최대 너비 설정
        textLabel.setFont(Font.font("Arial", 18)); // 원하는 폰트 및 크기 설정
        textLabel.setFill(Color.PURPLE);
        TextFlow textFlow = new TextFlow(textLabel);
        textFlow.setPrefWidth(200);

        VBox vboxItem = new VBox(); // 각 항목을 담을 VBox 생성
        vboxItem.getChildren().addAll(textFlow, hbox);

        Insets margin = new Insets(15); // 아래쪽 마진 값 설정
        VBox.setMargin(vboxItem, margin); // vboxItem에 마진 적용

        vbox.getChildren().add(vboxItem);
    }
}