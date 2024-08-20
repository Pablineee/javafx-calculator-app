package com.calculator.calculator_v3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("calculator_v3.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Calculator V3");
        stage.setScene(scene);
        stage.show();
        stage.setWidth(318.0);
        stage.setHeight(482.0);
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}