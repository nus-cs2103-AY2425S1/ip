package streams.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import streams.Streams;

/**
 * A GUI for Streams using FXML.
 */
public class Main extends Application {

    private final Streams stream = new Streams("./src/main/data/saveFile.txt");


    /**
     * Starts the GUI application by setting up the primary stage.
     * Loads the FXML layout for the main window, sets up the scene, and initializes
     * the controller with the Streams instance.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage should not be null";
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);

            stage.setTitle("Streams");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Root AnchorPane should not be null";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStream(stream);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

