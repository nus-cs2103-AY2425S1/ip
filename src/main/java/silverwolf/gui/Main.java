package silverwolf.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    public static final String DIRECTORY_PATH = "data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "silverWolf.txt";
    private final SilverWolf silverWolf = new SilverWolf(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            // current code...
            stage.setTitle("Silver Wolf");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(silverWolf); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
