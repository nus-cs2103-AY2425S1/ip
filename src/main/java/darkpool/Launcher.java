package darkpool;

import javafx.application.Application;
import darkpool.gui.Main;

/**
 * The Launcher class serves as the entry point for the JavaFX application.
 * It contains the main method which launches the JavaFX application.
 */
public class Launcher {
    /**
     * The main method which serves as the entry point for the Java application.
     * It launches the JavaFX application by calling Application.launch.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}