package victor;

import javafx.application.Application;

/**
 * Launcher class to work around classpath issues
 */
public class Launcher {
    /**
     * Calls the public void start(Stage) method in the Main class.
     * @param args CLI arguments passed to application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
