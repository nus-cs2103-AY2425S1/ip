package mryapper.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import mryapper.MrYapper;

/**
 * A GUI for MrYapper using FXML.
 */
public class Main extends Application {

    private MrYapper mrYapper = new MrYapper("data/tasks.txt");
    private final int MIN_WINDOW_HEIGHT = 500;
    private final int MIN_WINDOW_WIDTH = 400;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            setMinResolution(stage);
            stage.setTitle("MrYapper");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapper(mrYapper);  // inject the MrYapper instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMinResolution(Stage stage) {
        stage.setMinHeight(MIN_WINDOW_HEIGHT);
        stage.setMinWidth(MIN_WINDOW_WIDTH);
    }
}

