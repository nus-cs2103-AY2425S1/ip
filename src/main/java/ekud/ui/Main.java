package ekud.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Runs the EKuD chatbot GUI.
 *
 * @author uniqly
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // set title and icon
            stage.setTitle("EKuD");
            stage.getIcons().add(new Image("/images/upside-down.png"));

            // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setEkud();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
