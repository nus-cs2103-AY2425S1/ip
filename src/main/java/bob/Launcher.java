package bob;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues by launching the Main application.
 */
public class Launcher {

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
