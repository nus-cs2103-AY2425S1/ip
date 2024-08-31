package duck;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as a workaround to address classpath issues when launching a
 * JavaFX application. It delegates the responsibility of starting the application to the
 * {@link Main} class by invoking the {@link javafx.application.Application#launch(Class, String...)}
 * method.
 *
 * <p>This is particularly useful in environments where directly launching the JavaFX application
 * from the {@code Main} class might cause classpath-related problems. The {@code Launcher} class
 * ensures a clean separation between the application's entry point and the JavaFX launch process
 *
 * @see javafx.application.Application
 * @see Main
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
