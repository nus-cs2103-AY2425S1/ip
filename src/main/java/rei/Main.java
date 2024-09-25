package rei;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class to start the GUI for REI using FXML.
 */
public class Main extends Application {

    private Rei rei = new Rei("./data/rei.txt");

    /**
     * Starts the JavaFX application by setting up the main window.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Rei");
            stage.setMinHeight(820);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setRei(rei);  // inject the REI instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
