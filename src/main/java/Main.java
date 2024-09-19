
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pixel.Pixel;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Pixel duke = new Pixel("data.txt");
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setWidth(WIDTH);
            stage.setHeight(HEIGHT);
            fxmlLoader.<MainWindow>getController().setDuke(duke); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
