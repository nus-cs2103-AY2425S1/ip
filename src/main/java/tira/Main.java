package tira;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class, serves as entry point for JavaFX application
 * Initialises user interface by loading FMXL layouts
 */
public class Main extends Application {
    private Tira tira = new Tira("data/tira.txt");

    /**
     * Initializes the stage.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(tira); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
