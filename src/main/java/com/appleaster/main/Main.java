package com.appleaster.main;

import com.appleaster.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Appleaster appleaster = new Appleaster("data/tasks.txt");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Appleaster");
        stage.setScene(scene);
        stage.show();

        MainWindow mainWindow = fxmlLoader.getController();
        mainWindow.setAppleaster(appleaster);
    }

    public static void main(String[] args) {
        launch();
    }
}