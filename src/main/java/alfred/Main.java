package alfred;

import java.io.IOException;

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
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);  // inject the alfred instance

            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // Add this if you didn't automatically resize elements
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
