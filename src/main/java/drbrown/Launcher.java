package drbrown;

import drbrown.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to work around classpath issues in some environments.
 * This class serves as the entry point to launch the JavaFX application.
 * It avoids potential issues with launching JavaFX applications directly from certain IDEs or build tools.
 */
public class Launcher {

    /**
     * A launcher class to workaround classpath issues.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
