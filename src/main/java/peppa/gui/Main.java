package peppa.gui;

import java.io.IOException;

import peppa.Peppa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Peppa using FXML.
 */
public class Main extends Application {

    private Peppa peppa = new Peppa("data/peppa.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPeppa(peppa); // inject the Peppa instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
