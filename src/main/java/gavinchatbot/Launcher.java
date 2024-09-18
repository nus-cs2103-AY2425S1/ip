package gavinchatbot;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point to launch the GavinChatBot application.
 * It delegates to the JavaFX Application class to start the GUI.
 */
public class Launcher {

    /**
     * The main method that serves as the entry point for the Java application.
     * It launches the JavaFX application by calling {@link Application#launch(Class, String...)}.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
