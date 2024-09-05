package topaz.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import topaz.main.Topaz;

/**
 * Load MainWindow object and add it to scene, then add it to stage.
 */
public class MainUi extends Application {
    private Topaz topaz = new Topaz("data/Topaz.txt");
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainUi.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTopaz(topaz);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
