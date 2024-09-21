package puke.ui;

import java.awt.*;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import puke.Puke;

/**
 * Main application class for launching the JavaFX user interface.
 * This class sets up the primary stage and loads the main application window.
 */
public class Main extends Application {

    private Puke puke = new Puke();

    private javafx.scene.image.Image pukeImage = new Image(this.getClass().getResourceAsStream("/images/puke.png"));

    /**
     * Initializes and shows the primary stage of the application.
     * Loads the FXML layout for the main window and sets it up with a Puke instance.
     *
     * @param stage the primary stage for this application, onto which the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Puke");
            stage.getIcons().add(pukeImage);
            fxmlLoader.<MainWindow>getController().setPuke(puke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
