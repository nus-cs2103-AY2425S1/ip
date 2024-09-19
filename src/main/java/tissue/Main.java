package tissue;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tissue.window.MainWindow;

/**
 * Main application handler.
 */
public class Main extends Application {
    private static Logger logger = Logger.getLogger(Main.class.getName());
    private final Tissue tissue = new Tissue("./data/", "tissue.csv");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Main.class.getResource(("/view/MainWindow.fxml")));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Tissue bot");
            fxmlLoader.<MainWindow>getController().setTissue(tissue);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Sorry application met an error.");
        }
    }
}
