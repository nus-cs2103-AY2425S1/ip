package echo;

import echo.backend.Storage;
import echo.task.TaskList;
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
            ui.showLoadingError();
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

    public void stopRunning() {
        storage.saveToFile();
    }

    public String greetUser() {
        return ui.printWelcomeMsg();
    }
}
