package spike.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import spike.Spike;

/**
 * A GUI for Spike using FXML.
 */
public class Main extends Application {

    private Spike spike = new Spike();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Spike");
            Image appIcon = new Image("/images/spike_app_icon.png");
            stage.getIcons().add(appIcon);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setSpike(spike); // inject the Spike instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
