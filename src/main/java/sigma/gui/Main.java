package sigma.gui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sigma.Sigma;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    public static final String MAIN_WINDOW_FXML_FILE_PATH = "/view/MainWindow.fxml";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Sigma sigma = new Sigma();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML_FILE_PATH));
            assert fxmlLoader != null : "FXMLLoader should not be null.";
            Image icon = new Image("/images/gigachad.png");
            stage.getIcons().add(icon);
            stage.setTitle("Sigma");

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            LOGGER.info("Stage has been set.");
            fxmlLoader.<MainWindow>getController().setSigma(sigma);
            stage.show();
        } catch (IOException e) {
            LOGGER.warning("IOException occurred.");
            e.printStackTrace();
        }
    }
}
