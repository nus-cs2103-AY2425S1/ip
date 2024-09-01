package luke;

import javafx.application.Application;

/**
 * (From the SE-EDU JavaFX tutorial) A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(LukeUI.class, args);
    }
}
