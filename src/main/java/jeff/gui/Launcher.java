package jeff.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This class was taken from JavaFX Tutorial (https://se-education.org/guides/tutorials/javaFx.html).
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
