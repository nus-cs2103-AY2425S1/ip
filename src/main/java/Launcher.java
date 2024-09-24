import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Wojak.class, args);  // Start the Wojak application
    }
}
