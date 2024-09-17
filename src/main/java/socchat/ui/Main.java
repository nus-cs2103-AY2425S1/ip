package socchat.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import socchat.Socchat;

/**
 * A GUI for Socchat using FXML.
 */
public class Main extends Application {

    private Socchat socchat = new Socchat();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Socchat");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSocchat(socchat);  // inject the Socchat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
