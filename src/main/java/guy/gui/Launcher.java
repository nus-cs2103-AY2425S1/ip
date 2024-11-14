package guy.gui;

import javafx.application.Application;

/**
 * A launcher class, used to get around classpath issues.
 */
public class Launcher {
    public static void main(String... args) {
        Application.launch(Main.class, args);
    }
}
