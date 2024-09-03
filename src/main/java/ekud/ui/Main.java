package ekud.ui;

import ekud.Ekud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Runs the EKuD chatbot GUI.
 *
 * @author uniqly
 */
public class Main extends Application {
    private Ekud ekud;

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
            fxmlLoader.<MainWindow>getController().setEkud(ekud);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
