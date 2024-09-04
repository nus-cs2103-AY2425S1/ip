import dumpling.Ui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Obtained from JavaFx tutorial.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
