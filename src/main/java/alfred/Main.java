package alfred;

import java.io.IOException;

import alfred.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for alfred using FXML.
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "./data/Alfred.txt";

    private Alfred alfred = new Alfred(DEFAULT_FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // inject the alfred instance
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
