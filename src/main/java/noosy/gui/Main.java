package noosy.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import noosy.Noosy;

/**
 * A GUI for Noosy using FXML.
 * This class serves as the entry point for the Noosy application.
 * It extends the JavaFX Application class to set up and launch the GUI.
 */
public class Main extends Application {

    /**
     * The main Noosy instance used by the application.
     * It is initialized with data from a file named "noosy.txt" in the data directory.
     */
    private Noosy noosy = new Noosy("data/noosy.txt");

    /**
     * The main entry point for the JavaFX application.
     * This method is called after the init() method has returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create and set the scene
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Noosy");

            // Set stage properties
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417); // Set maximum width to prevent automatic resizing

            // Inject the Noosy instance into the controller
            fxmlLoader.<MainWindow>getController().setNoosy(noosy);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
