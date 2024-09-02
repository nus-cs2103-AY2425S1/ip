package monique;

import javafx.application.Application;

/**
 * A launcher class for starting the JavaFX application.
 * This class serves as an entry point to the application by launching the {@code Main} class.
 * It is used to work around classpath issues that might arise when running the JavaFX application directly.
 */
public class Launcher {

    /**
     * The main method to launch the JavaFX application.
     * It calls the {@code Application.launch()} method to start the {@code Main} class.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
