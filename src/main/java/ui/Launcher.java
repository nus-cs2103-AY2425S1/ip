package ui;

import javafx.application.Application;
import main.Main;

/**
 * A launcher class to work around classpath issues in some environments.
 * This class simply serves as an entry point for launching the main.Main class.
 */
public class Launcher {
    /**
     * The main method to launch the application using JavaFX's Application class.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
