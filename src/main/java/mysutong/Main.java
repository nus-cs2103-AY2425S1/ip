package mysutong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for MySutong using FXML.
 *
 * This class is the entry point for the JavaFX application.
 * It extends {@link javafx.application.Application} and sets up the main window
 * for the GUI using FXML.
 */
public class Main extends Application {

    private MySutong mysutong = new MySutong("data/tasks.txt");

    /**
     * Starts the JavaFX application.
     * This method is called when the JavaFX application is launched.
     * It loads the FXML layout for the main window, sets up the scene,
     * and displays the GUI.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Inject the MySutong instance into the controller
            fxmlLoader.<MainWindow>getController().setMySutong(mysutong);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
