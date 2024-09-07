import java.io.IOException;

import him.Him;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Him using FXML.
 */
public class Main extends Application {

    private Him him = new Him();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<control.MainWindow>getController().setHim(him);  // inject the Him instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}