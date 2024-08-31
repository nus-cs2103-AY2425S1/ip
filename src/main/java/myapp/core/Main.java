package myapp.core;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the BingBong application.
 * It initializes the necessary components, including the storage and bot, and starts the application.
 */
public class Main extends Application {

    /** The storage system used to persist tasks between sessions. */
    private Storage storage = new Storage("./data/tasks.txt");

    /** The BingBongBot that handles user interactions and commands. */
    private BingBongBot bot = new BingBongBot(storage);

    /**
     * Starts the JavaFX application by setting up the main window and displaying it.
     * The BingBongBot instance is passed to the main window to handle user input.
     *
     * @param stage The primary stage for this application, onto which the main window is set.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(stage);
        mainWindow.setBingBong(bot);
        mainWindow.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
