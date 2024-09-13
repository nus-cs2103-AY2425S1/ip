package nedui;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as the entry point for the JavaFX application.
 * It contains the {@code main} method which initiates the application by calling
 * {@code Application.launch()} with the {@code Main} class and any command-line arguments.
 *
 * <p>This class is necessary to work around a classpath issue that can occur when
 * launching JavaFX applications packaged in a JAR file. By using a separate launcher class,
 * it ensures that the JavaFX runtime is properly initialized, avoiding potential
 * {@code NoClassDefFoundError} or {@code ClassNotFoundException} at runtime.
 *
 * <p><strong>Usage:</strong> The {@code Launcher} class should be specified as the main class
 * in the JAR manifest or when running the application from the command line. This allows
 * the JavaFX application to start correctly regardless of how it is packaged or executed.
 *
 * @see javafx.application.Application
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
