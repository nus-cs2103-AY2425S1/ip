package choaticbot;

import java.io.IOException;

import choaticbot.storage.Storage;
import choaticbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for ChoaticBot using FXML.
 */
public class Main extends Application {

    private ChoaticBot choaticBot = new ChoaticBot();

    /**
     * The main entry point for the JavaFX application. Initializes the GUI and sets up the primary stage.
     * <p>
     * This method loads the FXML layout for the main window, sets the scene, and injects the {@code ChoaticBot}
     * instance into the controller. It also shows the main window.
     *
     * @param stage The primary stage for this application, onto which the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        Storage.loadTasksFromFile(choaticBot.getTasklist());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChoaticBot(choaticBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
