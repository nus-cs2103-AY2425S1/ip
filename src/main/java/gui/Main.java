package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.TaskFairy;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private TaskFairy taskFairy = new TaskFairy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskFairy(taskFairy);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
