package snowy;

import java.io.IOException;
import snowy.storage.Storage;
import snowy.tasklist.TaskList;
import snowy.parser.Parser;
import snowy.ui.MainWindow;
import snowy.ui.TextUi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snowy.Snowy;

/**
 * A GUI for Snowy using FXML.
 */
public class Main extends Application {
    private final Snowy snowy = new Snowy();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setSnowy(snowy);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
