package Majima.gui;

import javafx.application.Application;
import javafx.fxml.FXML;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        //Change the first argument class to whatever it is you're trying
        //to run.
    }
}
