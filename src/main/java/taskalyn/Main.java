package taskalyn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Taskalyn using FXML.
 */
public class Main extends Application {

    private Taskalyn taskalyn = new Taskalyn();

    /**
     * Starts Taskalyn application.
     *
     * @param stage Stage set
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskalyn(taskalyn);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
