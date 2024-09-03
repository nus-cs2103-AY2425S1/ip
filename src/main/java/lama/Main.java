package lama;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It initializes the application, loads the main GUI from an FXML file, and starts the application.
 */
public class Main extends Application {

    private Lama lama = new Lama("./data/lama.txt");

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * The method loads the main window layout from an FXML file and associates the Lama instance
     * with the controller.
     *
     * @param stage The primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLama(lama);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
