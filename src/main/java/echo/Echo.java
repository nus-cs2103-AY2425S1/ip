package echo;

import echo.backend.Storage;
import echo.task.TaskList;
import javafx.application.Platform;

/**
 * The Echo class serves as the main entry point for the Echo application.
 * It initializes the necessary components such as the TaskList, Ui, and Storage,
 * and manages the interaction between them.
 */
public class Echo {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    /**
     * Constructs an Echo object with the specified file path for storage.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Echo(String filePath) {
        this.storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (EchoException e) {
            ui.loadingErrorMessage();
            taskList = new TaskList();
        }
        ui = new Ui(taskList, this);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return ui.handleInput(input);
    }

    /**
     * Stops the application by saving the current task list to the storage file
     * and exiting the platform.
     */
    public void stopRunning() {
        assert storage != null: "Storage should not be null";
        storage.saveToFile();
        Platform.exit();
    }

    /**
     * Greets the user with a welcome message when the application starts.
     *
     * @return The welcome message as a String.
     */
    public String greetUser() {
        return ui.welcomeMessage();
    }
}
