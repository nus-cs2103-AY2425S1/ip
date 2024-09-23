package ava.gui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import ava.AVA;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Handles the GUI
 */
public class Gui extends Application {

    /**
     * Delay before exiting the GUI.
     * Delay is in milliseconds.
     */
    private static final int EXIT_DELAY = 600;
    private static MainWindow mainWindow;

    private final AVA ava;
    private final AssetManager assetManager;


    /**
     * Creates a GUI instance for a fresh AVA.
     */
    public Gui() {
        this(new AVA());
    }

    /**
     * Creates a GUI instance with the specified AVA instance.
     *
     * @param ava the AVA instance to be used
     */
    public Gui(AVA ava) {
        this.ava = ava;
        assetManager = new AssetManager();
    }

    /**
     * Displays the main GUI
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene will be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setAVA(ava);
            stage.getIcons().add(assetManager.getIcon());
            stage.setTitle("AVA");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renderError(String message) {
        mainWindow.displayError(message);
    }

    /**
     * Closes the GUI.
     */
    public static void close() {
        Timer timer = new Timer();
        TimerTask close = new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                // ends the program killing off any daemon threads.
                System.exit(0);
            }
        };
        timer.schedule(close, EXIT_DELAY);
    }
}
