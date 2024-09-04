package waterfall;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Waterfall using FXML.
 */
public class Main extends Application {

    private final Waterfall waterfall = new Waterfall("data/Tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinWidth(800);
            stage.setMinHeight(600);
            stage.setTitle("Waterfall");
            stage.setMaxWidth(1400);
            stage.setMaxHeight(1000);
            fxmlLoader.<MainWindow>getController().setWaterfall(waterfall); // inject the waterfall instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
