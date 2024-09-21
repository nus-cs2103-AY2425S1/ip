package main;

import blitz.Blitz;
import components.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main.Main class serves as the entry point for the Blitz chatbot application.
 * It sets up the primary stage and initializes the user interface.
 */
public class Main extends Application {

    private static final String STORAGE_FILE_PATH = "src/main/resources/data/blitz.txt";
    /** The Blitz chatbot instance initialized with a data file. */
    private final Blitz blitz = new Blitz(STORAGE_FILE_PATH);

    /**
     * The start method is the main entry point for JavaFX applications.
     * It sets up the primary stage, creates the main window, and displays the scene.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(blitz);
        mainWindow.load();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("Blitz");
        stage.show();
    }
}
