package thanos;

import javafx.application.Application;
import thanos.ui.Main;

/**
 * A class used to launch the JavaFX application.
 * <p>
 * The {@code Launcher} class contains the {@code main} method which serves as the entry point for starting
 * the JavaFX application by invoking the {@code Application.launch} method. This method will initiate the
 * JavaFX application lifecycle and call the {@code start} method in the {@code Main} class.
 * </p>
 */
public class Launcher {
    /**
     * The main method which serves as the entry point for the JavaFX application.
     * <p>
     * This method calls {@code Application.launch} to start the JavaFX application. It passes the {@code Main}
     * class as the application class to be launched, which will then trigger the JavaFX application lifecycle
     * and initialize the user interface.
     * </p>
     *
     * @param args the command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

