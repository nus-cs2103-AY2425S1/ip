package ollie.ui;

import javafx.application.Application;
import ollie.ui.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

