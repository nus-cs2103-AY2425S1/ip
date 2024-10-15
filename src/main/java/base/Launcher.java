package base;

import gui.Main;
import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for launching the JavaFX application.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
