package bob.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main method serves as the entry point for the application to launch the JavaFX application.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
