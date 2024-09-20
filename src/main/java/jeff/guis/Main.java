package jeff.guis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jeff.Jeff;

/**
 * A GUI for Jeff using FXML.
 */
public class Main extends Application {

    private Jeff jeff = new Jeff();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            stage.setTitle("Jeff");
            // Set the window icon as jeff
            Image icon = new Image(this.getClass().getResourceAsStream("/images/yoda.jpg"));
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setJeff(jeff); // inject the Jeff instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
