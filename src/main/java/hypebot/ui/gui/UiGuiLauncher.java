package hypebot.ui.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiGuiLauncher {
    public static void main(String[] args) {
        Application.launch(UiGuiMain.class, args);
    }
}
