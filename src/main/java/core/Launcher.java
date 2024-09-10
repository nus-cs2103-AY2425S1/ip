package core;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Defines the entry point into the GUI application!
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
