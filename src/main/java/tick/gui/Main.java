package tick.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tick.Tick;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Tick tick = new Tick();
    private Image tickIcon = new Image(this.getClass().getResourceAsStream("/images/Icon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Tick");
            stage.getIcons().add(tickIcon);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setTick(tick); // inject the Tick instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

