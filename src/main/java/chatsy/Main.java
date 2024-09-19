package chatsy;

import java.io.IOException;

import chatsy.gui.Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class that serves as the entry point of the Chatsy application.
 * It extends {@code Application} to launch the JavaFX graphical user interface (GUI) and coordinate with Chatsy.
 */
public class Main extends Application {
    private Chatsy chatsy = new Chatsy();
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the GUI layout
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Gui.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            // Set up the scene and stage for the GUI
            Scene scene = new Scene(anchorPane);
            stage.setTitle("Chatsy");
            stage.setScene(scene);

            fxmlLoader.<Gui>getController().setChatsy(chatsy);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
