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

            // Load custom fonts
            Font.loadFont(Main.class.getResource("/fonts/terminator.ttf").toExternalForm(), 10);
            Font.loadFont(Main.class.getResource("/fonts/Poppins-Regular.ttf").toExternalForm(), 10);

            // Create the scene
            Scene scene = new Scene(root);
            stage.setTitle("Terminator");
            stage.setScene(scene);

            // Set minimum size for the stage
            stage.setMinWidth(700);
            stage.setMinHeight(450);

            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading from fxml file.");
            System.exit(1);
        }
    }
}
