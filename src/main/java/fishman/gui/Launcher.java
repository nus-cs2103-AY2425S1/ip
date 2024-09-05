package fishman.gui;

import javafx.application.Application;

/**
 * The launcher class serves as the entry point for JavaFx. It contains the method which launches the JavaFx application
 * by invoking the launch method.
 */
public class Launcher {
    /**
     * The main method which serves as the entry point for the JavaFx application.
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
