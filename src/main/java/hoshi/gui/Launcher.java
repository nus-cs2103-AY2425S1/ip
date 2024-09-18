package hoshi.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches main class from here as a workaround.
     *
     * @param args default Java main args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
