package tars;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for TARS using FXML.
 */
public class Main extends Application {

    private Tars tars = new Tars();

    /**
     * Starts the GUI application.
     *
     * <p>This method is called when the JavaFX application is launched. It loads the FXML file
     * for the main window, sets up the primary stage with the scene, and injects the TARS instance
     * into the controller. The stage's minimum height and width are set to ensure the UI remains
     * properly visible.
     *
     * @param stage the primary stage for this application.
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
            fxmlLoader.<MainWindow>getController().setTars(tars);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
