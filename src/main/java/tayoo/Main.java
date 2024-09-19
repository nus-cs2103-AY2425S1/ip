package tayoo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Tayoo using FXML.
 */
public class Main extends Application {

    private Tayoo tayoo = new Tayoo();

    /**
     * Sets the scene of the GUI.
     * @param stage the stage on which to set the scene for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Tayoo");
            Image image = new Image("/images/icon.jpg");
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.setMinHeight(540);
            stage.setMinWidth(736);
            fxmlLoader.<MainWindow>getController().setTayoo(tayoo);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}