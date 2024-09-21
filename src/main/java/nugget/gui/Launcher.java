package nugget.gui;

import javafx.application.Application;

/**
 * The entry point for the Nugget Task Tracker application.
 * This class simply launches the JavaFX application by calling the main method.
 */
public class Launcher {
    /**
     * The main method, which serves as the entry point for the application.
     * Calls the {@code Application.launch()} method to start the JavaFX application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
