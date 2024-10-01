package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personalColor.MyInfo;
import personalColor.persistence.Colors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ColorController {
    @FXML private Button back_btn;
    @FXML private VBox vBox1;
    @FXML private VBox vBox2;
    @FXML private VBox vBox3;
    @FXML private VBox vBox4;
    @FXML private VBox vBox5;
    @FXML private Text season;
    int cnt = 1;
    private String seasonType;
    private String userID;
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
        Stage colorStage = (Stage) back_btn.getScene().getWindow();
        colorStage.close();
    }

    public void showColor(Colors color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(30);
        rectangle.setWidth(50);
        System.out.println((double)color.getR() + " " + color.getG() + " " +color.getB());
        rectangle.setFill(Color.rgb(color.getR(), color.getG(), color.getB()));

        Insets margin = new Insets(5); // 여기에 마진 값을 조정할 수 있습니다.
        VBox targetVBox;

        switch (cnt) {
            case 1:
                targetVBox = vBox1;
                break;
            case 2:
                targetVBox = vBox2;
                break;
            case 3:
                targetVBox = vBox3;
                break;
            case 4:
                targetVBox = vBox4;
                break;
            case 5:
                targetVBox = vBox5;
                break;
            default:
                targetVBox = null;
                break;
        }

        if (targetVBox != null) {
            VBox.setMargin(rectangle, margin);
            targetVBox.getChildren().add(rectangle);
        }

        if (cnt >= 5)
            cnt = 0;
        else
            cnt++;
    }
    public void initData(String userID, String seasonType)  throws IOException
    {
        this.userID = userID;
        this.seasonType = seasonType;
        season.setText(seasonType);
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }
}