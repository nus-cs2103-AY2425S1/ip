package choaticbot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The entry point of the application.
     * Launches the JavaFX application by calling the {@link Application#launch} method.
     *
     * @param args Command-line arguments to be passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
