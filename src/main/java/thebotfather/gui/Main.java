package thebotfather.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thebotfather.TheBotFather;

/**
 * The entry point for the graphical user interface (GUI) application of TheBotFather.
 * <p>
 * This class initializes the GUI using FXML and sets up the main window.
 */
public class Main extends Application {

    /** The instance of TheBotFather that handles the logic of the application. */
    private final TheBotFather bigBoss = new TheBotFather("./data/TheBotFather.txt");

    /**
     * Starts the GUI application.
     * <p>
     * Loads the FXML layout, sets the main window dimensions, and shows the application window.
     * Additionally, it passes the main bot instance to the controller to manage interactions.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(500);
            stage.setMinWidth(700);
            stage.setTitle("The BotFather");
            fxmlLoader.<MainWindow>getController().setBigBoss(bigBoss);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
