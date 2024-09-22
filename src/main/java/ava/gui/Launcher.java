package ava.gui;

import javafx.application.Application;

/**
 * Launcher to start the GUI.
 */
public class Launcher {

    /**
     * Runs AVA.
     * <br>
     * Main driver method running AVA.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
