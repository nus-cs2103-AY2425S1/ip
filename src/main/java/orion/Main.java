package orion;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Orion orion = new Orion();

    /**
     * Starts the Orion application.
     *
     * <p>
     * This is the entry point for the application. It loads the main window
     * FXML, sets the scene and title, and shows the stage.
     * </p>
     *
     * @param stage the stage to show the application in.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Orion");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setOrion(orion);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
