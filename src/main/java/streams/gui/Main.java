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
        try {
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            stage.setTitle("Streams");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStream(stream);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

