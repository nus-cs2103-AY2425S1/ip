package duke;

import javafx.application.Application;

/**
 * Entry point for launching the DailyTasks JavaFX application.
 *
 * <p>This class starts the application by invoking the {@link Application#launch}
 * method with {@link duke.DailyTasks} as the main application class.</p>
 */
public class Launcher {

    /**
     * Main method to launch the DailyTasks JavaFX application.
     *
     * @param args Command-line arguments passed to the application (if any).
     */
    public static void main(String[] args) {
        Application.launch(DailyTasks.class, args);
    }
}
