package tissue;

import java.io.IOException;

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

    private final Tissue tissue = new Tissue("./data/", "tissue.csv");

    @Override
    public void start(Stage stage) throws Exception {

        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Main.class.getResource(("/view/MainWindow.fxml")));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTissue(tissue);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
