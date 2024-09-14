package patrick;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Serves as the entry point for launching the GUI version of
 * the Patrick application using JavaFX and FXML.
 * It initializes the main application window and loads the FXML layout.
 */
public class Main extends Application {

    private Patrick patrick = new Patrick();

    /**
     * Starts the JavaFX application.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setPatrick(patrick); // inject the Patrick instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

