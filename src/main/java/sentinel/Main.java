package sentinel;

import java.io.IOException;

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

    private Sentinel sentinel = new Sentinel();

    private Image sentinelImage = new Image(this.getClass().getResourceAsStream("/images/DaSentinel.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sentinel");
            stage.getIcons().add(sentinelImage);
            fxmlLoader.<MainWindow>getController().setSentinel(sentinel); // inject the Duke instance
            stage.show();
            fxmlLoader.<MainWindow>getController().sentinelGreeting();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
