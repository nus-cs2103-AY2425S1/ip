package lbot;

import javafx.application.Application;
import lbot.gui.Main;

/**
 * Start point of LBot.
 */
public class Launcher {
    /**
     * Starts the GUI.
     *
     * @param args additional arguments to pass.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
