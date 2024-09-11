package donna;

import java.io.IOException;

import donna.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * GUI for Donna using FXML
 */
public class Main extends Application {
    private Donna donna = new Donna();
    private Ui ui = donna.getDonnaUi();

    /**
     * Starts the JavaFX application by setting up the scene and stage.
     *
     * @param stage the primary stage for this application, onto which
     *        the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Donna");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(634);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDonna(donna, ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
