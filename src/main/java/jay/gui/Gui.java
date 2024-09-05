package jay.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jay.Jay;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private final Jay jay = new Jay("Jay");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJay(jay); // inject the Jay instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

