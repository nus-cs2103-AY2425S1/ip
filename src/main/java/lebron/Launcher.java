package lebron;

import javafx.application.Application;

/**
 * The Launcher class is the entry point of the LeBron ChatBot application.
 * It serves as a workaround to launch the JavaFX application without
 * encountering module-related errors.
 */
public class Launcher {

    /**
     * The main method of the application. This method launches the JavaFX
     * application by calling the {@link Application#launch(Class, String...)}
     * method, which starts the JavaFX runtime and loads the specified main
     * application class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
