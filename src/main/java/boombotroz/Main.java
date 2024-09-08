package boombotroz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class of GUI.
 */
public class Main extends Application {

    private String home = System.getProperty("user.home");
    private java.nio.file.Path path = java.nio.file.Paths.get(home, "ip", "src", "main");
    private Boombotroz boombotroz = new Boombotroz(path.toString() + "/data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBoom(boombotroz);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


