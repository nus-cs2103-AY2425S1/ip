package mel.gui;

import javafx.application.Application;

/**
 * Launcher class serves as an entry point to GUI application,
 * to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to start up Mel chatbot.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
