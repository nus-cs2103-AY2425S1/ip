package skibidi.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skibidi.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Skibidi skibidi = new Skibidi("src/main/data/skibidi.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Skibidi");
            fxmlLoader.<MainWindow>getController().setSkibidi(skibidi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



