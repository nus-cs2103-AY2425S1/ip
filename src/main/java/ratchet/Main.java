package ratchet;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ratchet.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Ratchet ratchet = new Ratchet();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Ratchet");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRatchet(ratchet);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
