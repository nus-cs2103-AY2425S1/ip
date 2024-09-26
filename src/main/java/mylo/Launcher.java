package mylo;

import javafx.application.Application;
import mylo.ui.Gui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }

}
