package swbot.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The entry point of the application.
     * This main method serves as a workaround to launch the JavaFX application.
     *
     * @param args the command line arguments passed to this application. These arguments are forwarded
     *             to the JavaFX Application launch method.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

