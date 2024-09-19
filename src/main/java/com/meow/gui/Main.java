package com.meow.gui;

import java.io.IOException;

import com.meow.Meow;
import com.meow.Meowception;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Meow using FXML.
 */
public class Main extends Application {

    private Meow meow;

    @Override
    public void start(Stage stage) throws Meowception {
        try {
            meow = new Meow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/dialogbox.css").toExternalForm());
            stage.setScene(scene);
            
            fxmlLoader.<MainWindow>getController().setMeow(meow);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
