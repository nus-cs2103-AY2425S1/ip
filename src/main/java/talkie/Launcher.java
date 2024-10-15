package talkie;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 * <p>
 * The {@code Launcher} class serves as an entry point for the Talkie application. It is used to bypass classpath
 * issues that can occur when running JavaFX applications from certain IDEs or environments. This class directly
 * launches the {@code Main} class, which initializes and starts the JavaFX application.
 * </p>
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
