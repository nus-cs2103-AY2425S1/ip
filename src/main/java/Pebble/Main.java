package pebble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pebble pebble = new Pebble("data/pebble.txt");

    @Override
    public void start(Stage stage) {
        try {
            // setting limit to window size
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); //Add this if you didn't automatically resize elements

            // Set the window title
            stage.setTitle("Pebble Chat Bot");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPebble(pebble);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
