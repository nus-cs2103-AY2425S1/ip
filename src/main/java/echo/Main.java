package echo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * A GUI for Echo using FXML.
 */
public class Main extends Application {
    private final Echo echo = new Echo("src/main/data/savedTasks.txt");
    /**
     * Starts the JavaFX application by setting up the primary stage.
     * Loads the FXML layout, sets the scene, and initializes the controller
     * with the Echo instance.
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
            stage.setTitle("Echo");
            fxmlLoader.<MainWindow>getController().setEcho(echo);  // inject the Echo instance
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}