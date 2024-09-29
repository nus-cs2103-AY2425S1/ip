package torne.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * <p></p>
 * The entry point to our application.
 * Need to set in your `build.gradle`.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}