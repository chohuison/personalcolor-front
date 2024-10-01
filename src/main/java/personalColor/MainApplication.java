package personalColor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import personalColor.controller.LoginController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.util.Scanner;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        String ip = "192.168.2.101";

//        String ip="172.30.122.184"; // 아영
        String ip = "172.30.122.215"; //42
        //String ip = "192.168.25.38"; //이채티비

        Scanner s = new Scanner(System.in);
        boolean isContinue = true;

        MyInfo.setSocket(new Socket(ip,3000));
        System.out.println("connected!");

        DataInputStream is = new DataInputStream(MyInfo.socket.getInputStream());
        DataOutputStream os = new DataOutputStream(MyInfo.socket.getOutputStream());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Login-view.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.initData();

        Scene scene = new Scene(root);
        stage.setTitle("What's your color?");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}