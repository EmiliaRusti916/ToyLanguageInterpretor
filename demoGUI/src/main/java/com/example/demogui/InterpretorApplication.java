package com.example.demogui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.demogui.DisplayListController;

public class InterpretorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(InterpretorApplication.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader1.load());
        MainController mainCtrl = fxmlLoader1.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader(InterpretorApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());
        DisplayListController ctrl2 = fxmlLoader2.getController();

        mainCtrl.setController(ctrl2);


        Stage stage2=new Stage();
        stage2.setTitle("Select an example");
        stage2.setScene(scene2);
        stage2.show();

        stage.setTitle("Main Window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

