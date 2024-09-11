package gui;

import javafx.application.Application;

import michael.Michael;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Michael.class, args);
    }
}
