package delta.gui;

import java.io.IOException;

import delta.Delta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Delta using FXML.
 */
public class Main extends Application {

    private Delta delta = new Delta("data/tasks.txt");

    /**
     * Sets the stage and scene for the Delta ChatBot application.
     *
     * @param stage Stage to run Delta ChatBot.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Delta ChatBot");
            stage.setMinHeight(643);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setDelta(delta); // inject the Delta instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

