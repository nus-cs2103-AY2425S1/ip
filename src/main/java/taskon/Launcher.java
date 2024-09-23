package taskon;

import javafx.application.Application;

/**
 * Provides a launcher class to work around classpath issues.
 */
public class Launcher {
    /**
     * Launches the Taskon application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
