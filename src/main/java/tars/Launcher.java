package tars;

import javafx.application.Application;

/**
 * @@author SKarthikeyan28 --reused
 * Code from SE Student Projects JavaFX guide (https://se-education.org/guides/tutorials/javaFx.html)
 * with minor modifications.
 *
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Acts as the main entry point for the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
