package sigma;

import javafx.application.Application;

/**
 * The {@code Launcher} class serves as an entry point for launching the Sigma application.
 * This class exists to work around classpath issues that may arise when starting a JavaFX
 * application directly from the main application class.
 *
 * <p>This class delegates the launch process to the {@link Main} class by calling
 * {@code Application.launch}, ensuring that the JavaFX runtime is correctly initialized.
 */
public class Launcher {
    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
