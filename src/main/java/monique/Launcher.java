package monique;

import javafx.application.Application;

/**
 * A launcher class for starting the JavaFX application.
 * This class serves as an entry point to the application by launching the {@code Main} class.
 * It is used to work around classpath issues that might arise when running the JavaFX application directly.
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     * This method calls {@code Application.launch(Main.class, args)} to start the JavaFX application
     * by initializing the {@code Main} class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
