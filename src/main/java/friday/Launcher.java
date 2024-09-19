package friday;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 * This class serves as the entry point to launch the JavaFX application.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
