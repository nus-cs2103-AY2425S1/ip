import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application class for running the GUI for the Alex chatbot using JavaFX and FXML.
 * Initializes the application and sets up the primary stage.
 */
public class Main extends Application {

    // Instance of the Alex chatbot with the path to the tasks file
    private Alex alex = new Alex("data/tasks.txt");

    /**
     * Entry point for the JavaFX application.
     * Sets up the primary stage, loads the FXML layout, and initializes the scene.
     *
     * @param stage The primary stage for this application, onto which scenes can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML layout for the main window
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Create a scene with the loaded layout
            Scene scene = new Scene(ap);

            // Set the scene on the primary stage and configure stage properties
            stage.setScene(scene);
            stage.setMinHeight(220); // Set minimum height of the window
            stage.setMinWidth(417); // Set minimum width of the window

            // Inject the Alex instance into the MainWindow controller
            fxmlLoader.<MainWindow>getController().setAlex(alex);

            // Show the primary stage
            stage.show();
        } catch (IOException e) {
            // Print stack trace if there is an error loading the FXML file or setting up the stage
            e.printStackTrace();
        }
    }
}

