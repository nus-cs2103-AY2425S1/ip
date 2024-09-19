package gui;

import FRIDAY.FRIDAY;

import java.awt.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for FRIDAY using FXML.
 */
public class Main extends Application {

    private FRIDAY FRIDAY = new FRIDAY();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("FRIDAY");
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setFRIDAY(FRIDAY);
            stage.show();
        } catch (IOException e) {
            System.out.println("Input error");
        }
    }
}

