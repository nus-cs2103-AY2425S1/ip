package jeff.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jeff.Jeff;

/**
 * A GUI for Jeff using FXML.
 * This class was taken from JavaFX Tutorial (https://se-education.org/guides/tutorials/javaFx.html).
 */
public class Main extends Application {

    private Jeff jeff = new Jeff("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jeff");
            fxmlLoader.<MainWindow>getController().setJeff(jeff);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
