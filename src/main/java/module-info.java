module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens personalColor to javafx.fxml;
    exports personalColor;
    exports personalColor.controller;
    opens personalColor.controller to javafx.fxml;
}