package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Duke application.
 * It extends javafx.application.Application and sets up the main window
 * of the application using JavaFX.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the JavaFX application by setting up the main window.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().sendGreeting();// inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
