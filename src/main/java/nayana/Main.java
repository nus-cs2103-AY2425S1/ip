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
    // Creates an instance of Nayana
    private Nayana nayana = new Nayana();
    @Override
    public void start(Stage stage) {
        try {
            // Loads the FXML layout from the specified file.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            //Apply CSS stylesheet
            stage.setScene(scene); // Creates a new Scene with the loaded layout.
            stage.setTitle("Resizable Window");
            stage.setMinWidth(400); // Optional: Set minimum width
            stage.setMinHeight(630); // Optional: Set minimum height

            // Injects the Nayana instance into the MainWindow controller.
            fxmlLoader.<MainWindow>getController().setNayana(nayana);
            stage.show(); // Displays the primary stage.

        } catch (IOException e) {
            e.printStackTrace(); // Handles potential I/O errors during FXML loading.
        }
    }
}
