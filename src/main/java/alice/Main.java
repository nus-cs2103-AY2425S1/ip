package alice;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * The Main class set up and launch the JavaFX application.
 */
public class Main extends Application {

    private Alice alice = new Alice();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<alice.ui.MainWindow>getController().setAlice(alice);
            stage.setOnCloseRequest(event -> handleCloseRequest(event));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCloseRequest(WindowEvent event) {
        try {
            alice.getStorage().save(alice.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
