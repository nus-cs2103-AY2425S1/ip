package thanos;

import javafx.application.Application;
import thanos.ui.Main;

/**
 * A class used to launch the JavaFX application.
 */
public class Launcher {
    /**
     * Serves as the entry point for the JavaFX application.
     *
     * @param args the command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

