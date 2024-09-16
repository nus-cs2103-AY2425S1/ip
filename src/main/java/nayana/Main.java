package nayana;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    // Creates an instance of Nayana with the specified file path.
    private Nayana nayana = new Nayana("data/nayana.txt");
    @Override
    public void start(Stage stage) {
        try {
            // Loads the FXML layout from the specified file.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene); // Creates a new Scene with the loaded layout.
            // Injects the Nayana instance into the MainWindow controller.
            fxmlLoader.<MainWindow>getController().setNayana(nayana);
            stage.show(); // Displays the primary stage.

        } catch (IOException e) {
            e.printStackTrace(); // Handles potential I/O errors during FXML loading.
        }
    }
}
