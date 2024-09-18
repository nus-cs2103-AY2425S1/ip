package lebron;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for launching the GUI of the LeBron
 * ChatBot application using JavaFX. It initializes the JavaFX application and
 * sets up the main window for user interaction.
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "./data/lebron.txt"; // Default file path for storing tasks

    private LeBron lebron = new LeBron(DEFAULT_FILE_PATH); // Instance of the LeBron application

    /**
     * Starts the JavaFX application by setting up the primary stage, loading
     * the FXML layout, and initializing the controller with the LeBron
     * instance.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("LeBron James - ChatBot");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(lebron.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the LeBron instance into the controller
            fxmlLoader.<lebron.MainWindow>getController().setDuke(lebron);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
