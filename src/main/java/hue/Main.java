package hue;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Hue using FXML.
 */
public class Main extends Application {

    private Hue hue = new Hue("data/Hue.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Hue");
            stage.getIcons().add(new Image("/images/DaHue.png"));
            fxmlLoader.<MainWindow>getController().setHue(hue);  // inject the Hue instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
