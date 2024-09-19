package base;

import gui.Main;
import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for launching the JavaFX application.
 * It delegates the task of starting the application to the {@code Application.launch()} method.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application by calling {@code Application.launch()}.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
