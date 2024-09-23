package edith;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * <p>
 * This class is required to launch the application due to certain
 * limitations with launching JavaFX applications directly from an executable JAR.
 * It serves as a wrapper to call the Main class, which contains the
 * actual application logic.
 * </p>
 */
public class Launcher {
    /**
     * The main method that launches the Edith GUI application.
     * This method delegates the application startup to the Main class, which is responsible
     * for setting up and displaying the main window of the application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
