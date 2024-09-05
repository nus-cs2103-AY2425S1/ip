package elster;

import javafx.application.Application;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
