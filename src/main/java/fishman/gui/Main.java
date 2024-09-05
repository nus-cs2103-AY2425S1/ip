package fishman.gui;

import java.io.IOException;

import fishman.Fishman;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the graphical user interface that is used for user interactions.
 */
public class Main extends Application {
    private final Fishman fishman = new Fishman();

    /**
     * The main entry point for the application. This method is called when the application is launched.
     *
     * @param stage The primary stage of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fishman");
            fxmlLoader.<MainWindow>getController().setFishman(fishman);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the application when the command is given. It also saves data before the application exits.
     */
    @Override
    public void stop() {
        fishman.loadAndSaveTasks("save");
    }
}
