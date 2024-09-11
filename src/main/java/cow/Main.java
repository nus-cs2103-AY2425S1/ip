package cow;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Main class.
 */
public class Main extends Application {
    private Cow cow = new Cow("data/cow.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            setStage(stage, scene);
            fxmlLoader.<MainWindow>getController().setCow(cow); // inject the Cow instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.setMinHeight(220);
        stage.setMinWidth(417);
        stage.setTitle("Cow");
    }
}
