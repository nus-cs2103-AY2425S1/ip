package gui;

import javafx.application.Application;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Hyperion;

/**
 * A GUI for Hyperion using FXML.
 */
public class Main extends Application {

    private Hyperion hyperion = new Hyperion();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHyperion(hyperion);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
