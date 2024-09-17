package bobby;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Serves as the entry point to the program.
     * It launches the JavaFX application by calling the launch() method.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
