package pikappi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pikappi.exception.PikappiException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pikappi pikappi = new Pikappi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPikappi(pikappi); // inject the Pikappi instance
            pikappi.loadTasks();
            stage.setTitle("Pikappi");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PikappiException e) {
            throw new RuntimeException(e);
        }
    }
}
