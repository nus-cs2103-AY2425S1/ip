package duke;
import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(new Duke("data/duke.txt"));
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
