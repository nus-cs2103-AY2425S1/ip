import java.io.IOException;

import carine.exceptions.InvalidTaskInDatabaseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Carine using FXML.
 */
public class Main extends Application {

    private Carine carine;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Carine");
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            try {
                carine = new Carine();
                controller.setCarine(carine);
            } catch (InvalidTaskInDatabaseException e) {
                controller.setCarineWithError(e.toString());
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

