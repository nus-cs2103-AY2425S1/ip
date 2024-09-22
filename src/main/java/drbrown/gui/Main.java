package drbrown.gui;

import java.io.IOException;

import drbrown.DrBrown;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for DrBrown using FXML.
 * This class extends the JavaFX Application class and serves as the entry point
 * for the GUI-based version of the DrBrown application.
 */
public class Main extends Application {

    private final DrBrown drBrown = new DrBrown(); // Made final

    /**
     * Starts the GUI application by setting up the primary stage.
     * Loads the FXML layout for the main window, sets up the scene, and initializes
     * the controller with the DrBrown instance.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Optionally apply a stylesheet (if you have one)
            // scene.getStylesheets().add(getClass().getResource("/path/to/your/stylesheet.css").toExternalForm());

            stage.setTitle("DrBrown Task Manager"); // Set window title
            stage.setScene(scene);

            // Set the DrBrown instance to the controller
            fxmlLoader.<MainWindow>getController().setDrBrown(drBrown);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
            // Optionally, show an error dialog to the user
        }
    }

    /**
     * The main entry point for launching the application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
