package main.java.angel;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 * This class serves as the entry point for the JavaFX application and launches the GUI.
 */
public class Launcher {

    /**
     * The main method that serves as the entry point of the application.
     * This method launches the JavaFX application by calling Application.launch with the specified Gui class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Assert that args array is not null before proceeding
        assert args != null : "Command-line arguments array is null";

        // Assert that Application.launch can be called with non-null args
        assert Gui.class != null : "Gui class reference is null";

        Application.launch(Gui.class, args);
    }
}
