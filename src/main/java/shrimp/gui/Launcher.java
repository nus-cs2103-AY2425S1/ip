package shrimp.gui;

import javafx.application.Application;

/**
 * The Launcher class is the entry point to the application.
 * It is responsible for launching the JavaFX application.
 */
public class Launcher {

    /**
     * The main method serves as the entry point of the program.
     * It launches the JavaFX application by calling {@link Application#launch(Class, String...)}.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
