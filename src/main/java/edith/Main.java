package edith;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for the Edith GUI app.
 * This class extends the Application class from JavaFX to create and launch the GUI.
 */
public class Main extends Application {

    /** Instance of the Edith chatbot that handles task management and user interactions. */
    private Edith edith = new Edith("./data/edith.txt");

    /**
     * Starts the JavaFX application.
     * This method sets up the main window (stage) and loads the FXML layout for the application.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEdith(edith);  // Inject the Edith instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
