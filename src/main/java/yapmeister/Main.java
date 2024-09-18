package yapmeister;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yapmeister.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private YapMeister yapMeister = new YapMeister("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(yapMeister.getName());
            fxmlLoader.<MainWindow>getController().setYapMeister(yapMeister);  // inject the Yapmeister instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
