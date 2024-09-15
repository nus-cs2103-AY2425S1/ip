import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rudolf.Rudolf;

/**
 * A GUI for Rudolf using FXML. This class initializes the main application window
 * and sets up the primary stage with the corresponding FXML layout.
 */
public class Main extends Application {

    private final Rudolf rudolf = new Rudolf("data/rudolf.txt");

    /**
     * The main entry point for the JavaFX application. This method sets up the
     * primary stage, loads the FXML layout, and displays the main window.
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
            fxmlLoader.<MainWindow>getController().setRudolf(rudolf);  // Injects the Rudolf instance into the controller
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
