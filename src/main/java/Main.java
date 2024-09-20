import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import luna.Luna;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Luna luna = new Luna();

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(500);
            stage.setMinWidth(350);
            stage.setTitle("Luna");

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setLuna(luna); // inject the Luna instance

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
