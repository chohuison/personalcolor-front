package personalColor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import personalColor.persistence.Cosmetic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CosmeticsController {

    @FXML private Button back_btn;
    @FXML private Button lip;
    @FXML private Button shadow;
    @FXML private Button blusher;

    @FXML private VBox libBox;
    @FXML private VBox blusherBox;
    @FXML private VBox shadowBox;

    boolean libCheck = false;
    boolean shadowCheck = false;
    boolean blusherCheck = false;

    private String seasonType;
    private String userID;
    private List<Cosmetic> cosmeticList = new ArrayList<>();
    private DataOutputStream dos;
    private DataInputStream dis;

    public void handleButtonLip(ActionEvent event) throws IOException, URISyntaxException
    {
        libBox.setVisible(true);
        shadowBox.setVisible(false);
        blusherBox.setVisible(false);

        if(libCheck == false)
        {
            showCosmetic("립스틱", libBox);
            libCheck = true;
        }
    }

    public void handleButtonShadow(ActionEvent event) throws IOException, URISyntaxException
    {
        libBox.setVisible(false);
        shadowBox.setVisible(true);
        blusherBox.setVisible(false);

        if(shadowCheck == false)
        {
            showCosmetic("아이섀도우", shadowBox);
            shadowCheck = true;
        }
    }

    public void handleButtonBlusher(ActionEvent event) throws IOException, URISyntaxException
    {
        libBox.setVisible(false);
        shadowBox.setVisible(false);
        blusherBox.setVisible(true);

        if(blusherCheck == false)
        {
            showCosmetic("블러셔", blusherBox);
            blusherCheck = true;
        }
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
        Stage cosmeticStage = (Stage) back_btn.getScene().getWindow();
        cosmeticStage.close();
    }

    public void initData(String userID, String seasonType)  throws IOException
    {
        this.userID = userID;
        this.seasonType = seasonType;
        dis = new DataInputStream(MyInfo.socket.getInputStream());
        dos = new DataOutputStream(MyInfo.socket.getOutputStream());
    }

    public void addCosmeticList(List<Cosmetic> cosList)
    {
        if(cosmeticList.size() != 0)
            cosmeticList.clear();

        for(int i = 0; i < cosList.size(); i++)
            cosmeticList.add(cosList.get(i));
    }

    public void showCosmetic(String productType, VBox vBox) throws URISyntaxException
    {
        for(int i = 0; i < cosmeticList.size(); i++)
        {
            if(cosmeticList.get(i).getProductType().equals(productType))
            {
                HBox hbox = new HBox();
                VBox subVbox = new VBox();

                URI imageUri = new URI(cosmeticList.get(i).getCosRoot());
                System.out.println(imageUri);
                Image image = new Image(imageUri.toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(120);
                imageView.setFitWidth(120);

                Text brandName = new Text(cosmeticList.get(i).getBrandName());
                Text productName = new Text(cosmeticList.get(i).getProductName());
                brandName.setWrappingWidth(100);
                productName.setWrappingWidth(200);
                brandName.setFont(Font.font("Arial", 14));
                productName.setFont(Font.font("Arial", 14));
                brandName.setFill(Color.PURPLE);
                productName.setFill(Color.PURPLE);

                TextFlow blank = new TextFlow(new Text(" "));
                TextFlow text1 = new TextFlow(brandName);
                TextFlow text2 = new TextFlow(productName);

                subVbox.getChildren().addAll(text1, text2);
                hbox.getChildren().addAll(imageView, subVbox);
                vBox.getChildren().addAll(hbox, blank);
            }
        }
    }

}
