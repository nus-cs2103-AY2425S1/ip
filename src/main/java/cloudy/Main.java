package cloudy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cloudy using FXML.
 */
public class Main extends Application {

    private Cloudy cloudy = new Cloudy("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCloudy(cloudy);  // inject Cloudy instance
            stage.setTitle("Cloudy bot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
