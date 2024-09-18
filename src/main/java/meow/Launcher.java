package meow;

import javafx.application.Application;

// Solution adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * The Launcher class is the entry point to the application.
 * It launches the JavaFX application
 */
public class Launcher {

    /**
     * Standard main (psvm) method
     *
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

