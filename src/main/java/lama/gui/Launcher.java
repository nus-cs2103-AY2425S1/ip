package lama.gui;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for the JavaFX application.
 * It is responsible for launching the JavaFX application by invoking the Application method.
 * This class delegates the main responsibilities to the Main class, which extends Application.
 */
public class Launcher {
    /**
     * The main method that serves as the entry point of the JavaFX application.
     * It calls the Application method to start the JavaFX lifecycle.
     *
     * @param args The command line arguments passed to the application.
     *             Any arguments needed by the application should be specified here.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
