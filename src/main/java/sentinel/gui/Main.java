package sentinel.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sentinel.Sentinel;

/**
 * A GUI for Sentinel using FXML.
 */
public class Main extends Application {

    private final Sentinel sentinel = new Sentinel();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sentinel");
            fxmlLoader.<MainWindow>getController().setSentinel(sentinel); // inject the Sentinel instance
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
