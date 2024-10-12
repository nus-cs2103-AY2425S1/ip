package evan.gui;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(evan.gui.Main.class, args);
    }
}
