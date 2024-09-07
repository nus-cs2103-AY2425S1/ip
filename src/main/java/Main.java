import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import gui.MainWindow;
import nixy.Nixy;

/**
 * A GUI for Nixy using FXML.
 */
public class Main extends Application {

    private Nixy nixy = new Nixy("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNixy(nixy);  // inject the Nixy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
