package terminator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Entry point for the JavaFX application.
 */
public class Main extends Application {

    /**
     * Starts the GUI for the chatbot.
     *
     * @param stage The JavaFX Stage.
     */
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainController.fxml"));
        try {
            // Load root node from FXML file
            AnchorPane root = fxmlLoader.load();

            // Load custom font
            Font.loadFont(Main.class.getResource("/fonts/terminator.ttf").toExternalForm(), 10);

            // Create and show the scene
            Scene scene = new Scene(root);
            stage.setTitle("Terminator");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading from fxml file.");
            System.exit(1);
        }
    }
}
