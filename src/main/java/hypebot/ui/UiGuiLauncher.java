package hypebot.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class UiGuiLauncher {
    public static void main(String[] args) {
        Application.launch(UiGuiMain.class, args);
    }
}
