package mysutong;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * This class serves as an entry point to launch the JavaFX application.
 * It exists primarily to address classpath issues that might occur when launching
 * the application directly from certain environments (like IDEs or build tools).
 * The `main` method calls `Application.launch()`, which internally starts the
 * JavaFX application lifecycle.
 */
public class Launcher {

    /**
     * The main method that serves as the entry point for the Java application.
     * It launches the JavaFX application by calling {@link Application#launch(Class, String...)}.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
