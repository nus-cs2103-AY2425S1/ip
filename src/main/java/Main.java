import java.io.IOException;

import carine.exceptions.InvalidTaskInDatabaseException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            try {
                carine = new Carine();
            } catch (InvalidTaskInDatabaseException e) {
                showAlert("Database Error", e.toString());
                Platform.exit();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Carine");
            fxmlLoader.<MainWindow>getController().setCarine(carine);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
