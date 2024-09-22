package sigma.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert Application.class != null : "JavaFX Application class is missing.";
        assert Main.class != null : "Main class is missing.";
        Application.launch(Main.class, args);
    }
}
