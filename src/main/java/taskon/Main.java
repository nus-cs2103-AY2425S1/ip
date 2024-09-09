package taskon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Taskon using FXML.
 */
public class Main extends Application {

    private Taskon taskon = new Taskon();

    /**
     * Starts the JavaFX application by loading the FXML layout and
     * initializing the primary stage.
     *
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskon(taskon); // inject the Taskon instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
