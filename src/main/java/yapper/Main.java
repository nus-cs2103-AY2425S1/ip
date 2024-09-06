package yapper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The type Main.
 */
public class Main extends Application {
    private Yapper yapper = new Yapper("data/tasks.txt");
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapper(yapper); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}