package futureyou.gui;
import java.io.IOException;

import futureyou.FutureYou;
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

    private final FutureYou futureYou;

    {
        try {
            futureYou = new FutureYou();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Future You");
            // Application icon
            Image img = new Image("/images/futureYou.png");
            stage.getIcons().add(img);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFutureYou(futureYou);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
