package luffy;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class LuffyLauncher {
    public static void main(String[] args) {
        Application.launch(LuffyMain.class, args);
    }
}
