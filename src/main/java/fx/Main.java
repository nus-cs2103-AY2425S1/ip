package fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skd.SkD;

/**
 * A GUI to implement SkD using FXML.
 */
public class Main extends Application {

    private SkD skD = new SkD("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("SkD");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(417);
            fxmlLoader.<MainWindow>getController().setSkd(skD);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
