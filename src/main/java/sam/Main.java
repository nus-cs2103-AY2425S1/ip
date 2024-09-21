package sam;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point for the application.
 * It extends the JavaFX Application class and provides the start method.
 * The start method loads the MainWindow.fxml file, sets up the scene, and displays the stage.
 * It also injects an instance of the Sam class into the MainWindow controller.
 */
public class Main extends Application {

    private Sam sam = new Sam();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Sam Chatbot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(sam); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
