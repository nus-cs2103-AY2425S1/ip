package bob;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main entry point for the Bob chatbot application.
 * Initializes the GUI using FXML and sets up the main application stage.
 */
public class Main extends Application {

    private Bob bob = new Bob();

    /**
     * Starts the application and sets up the primary stage with the main GUI.
     * Loads the FXML file for the main window, sets up the Bob instance, and displays the welcome message.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Set the Bob instance in the controller
            fxmlLoader.<MainWindow>getController().setBob(bob);

            // Display the welcome message
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();

            // Set up the scene and stage
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
