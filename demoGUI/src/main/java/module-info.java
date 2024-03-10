module com.example.demogui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;

    opens model to javafx.base;
    opens com.example.demogui to javafx.fxml;
    exports com.example.demogui;
}