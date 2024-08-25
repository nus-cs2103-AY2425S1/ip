package hoodini;

import java.util.logging.Logger;

import javafx.application.Application;



/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    private static final Logger logger = Logger.getLogger(Launcher.class.getName());

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

