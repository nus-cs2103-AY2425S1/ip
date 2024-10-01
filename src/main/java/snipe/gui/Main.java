package snipe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snipe.core.Snipe;

/**
 * Main class for the GUI application of Snipe using JavaFX and FXML.
 * This class is responsible for initializing and displaying the main window
 * of the Snipe application.
 */
public class Main extends Application {

    /**
     * An instance of the Snipe core application logic.
     * This is injected into the controller for the GUI.
     */
    private Snipe snipe = new Snipe("data");

    /**
     * Starts the JavaFX application.
     * This method is called when the application is launched, setting up
     * the primary stage and loading the FXML layout for the main window.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("Snipe");

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setSnipe(snipe);  // inject the Snipe instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
