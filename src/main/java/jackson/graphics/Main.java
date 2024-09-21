package jackson.graphics;

import java.io.IOException;

import jackson.Jackson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jackson jackson = new Jackson();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jackson Bot");

            // Image and tab title for window
            Image windowImage = new Image(getClass().getResourceAsStream("/images/Jackson_Icon.png"));
            stage.getIcons().add(windowImage);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setJackson(jackson); // inject the Jackson instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
