package mryapper.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mryapper.MrYapper;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private MrYapper mrYapper = new MrYapper("src/data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setMinHeight(500);
            stage.setMinWidth(400);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapper(mrYapper);  // inject the MrYapper instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

