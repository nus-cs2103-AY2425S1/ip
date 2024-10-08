package bob;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(MainWindow.class, args);
    }
}
