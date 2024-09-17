package lemon.app;

import javafx.application.Application;
import lemon.ui.LemonFx;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(LemonFx.class, args);
    }
}
