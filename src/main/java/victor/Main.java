package victor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import victor.controls.MainWindow;

/**
 * Main class that extends Java FX application.
 */
public class Main extends Application {
    /**
     * Called by the Launcher main method.
     * Initialises program handler of requests and stage for application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHandler();
            fxmlLoader.<MainWindow>getController().setScrollListener();
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().welcomeUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
