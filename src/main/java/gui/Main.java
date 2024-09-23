package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rizzler.Rizzler;

/**
 * Represents a GUI for Rizzler using FXML.
 */
public class Main extends Application {
    private final Rizzler rizzler = new Rizzler();

    /**
     * Creates the stage for display of GUI elements.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage must not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRizzler(rizzler);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
