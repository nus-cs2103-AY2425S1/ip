package edith;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * <p>
 * This class is required to launch the application due to certain
 * limitations with launching JavaFX applications directly from an executable JAR.
 * It serves as a wrapper to call the {@link Main} class, which contains the
 * actual application logic.
 * </p>
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
