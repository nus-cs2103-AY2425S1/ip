package lama.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lama.Lama;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It initializes the application, loads the main GUI from an FXML file, and starts the application.
 */
public class Main extends Application {

    private Lama lama = new Lama("./data/lama.txt", "./data/alias.txt");

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
            AnchorPane anchorPane = loadMainWindow();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load the main window layout.");
            e.printStackTrace();
        }
    }

    private AnchorPane loadMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        MainWindow controller = fxmlLoader.<MainWindow>getController();
        controller.setLama(lama);
        return anchorPane;
    }


}
