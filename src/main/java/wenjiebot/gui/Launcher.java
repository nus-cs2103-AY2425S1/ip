package wenjiebot.gui;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues when launching the application.
 * This class delegates the task of launching the JavaFX application to the {@link Main} class.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
