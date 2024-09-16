package mel.gui;

import javafx.application.Application;

/**
 * Launcher class serves as an entry point to GUI application,
 * to workaround classpath issues.
 */
public class Launcher {
    /**
     * Starts up Mel chatbot.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
