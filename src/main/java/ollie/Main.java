package ollie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.exit;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application implements ExitHandler {
    private static final String filepath = "./data/tasks.txt";
    private Ollie ollie = new Ollie(filepath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setOllie(ollie);
            fxmlLoader.<MainWindow>getController().setExitHandler(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExit() {
        exit();
    }
}

