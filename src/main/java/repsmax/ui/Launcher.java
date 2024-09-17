package repsmax.ui;

import javafx.application.Application;
import repsmax.ui.RepsmaxGui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(RepsmaxGui.class, args);
    }
}
