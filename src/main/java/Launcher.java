import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert args != null : "Command-line arguments should not be null"; // Assert that the arguments are not null
        Application.launch(Main.class, args);
    }
}
