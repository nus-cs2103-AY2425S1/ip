package kat;

import javafx.application.Application;

/**
 * Entry point for the JavaFX application.
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     * It calls the launch method of the Application class, passing the Main class and any command-line arguments.
     *
     * @param args Command line arguments to be passed to the JavaFX application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
