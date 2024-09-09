package joe.main;

import java.io.FileNotFoundException;

import joe.exceptions.CorruptedFileException;
import joe.tasks.TaskList;
import joe.utils.Storage;

/**
 * Represents the main class of the programme.
 */
public class Joe {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Joe.
     *
     * @param filePath the file path to retrieve and store the tasks
     */
    public Joe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = initializeTasks();
        } catch (FileNotFoundException | CorruptedFileException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
        ui.setTasks(tasks);
    }

    /**
     * Initializes the tasks from the file.
     *
     * @return a TaskList object containing the tasks
     * @throws FileNotFoundException  if the file is not found
     * @throws CorruptedFileException if the file is corrupted
     */
    public TaskList initializeTasks() throws FileNotFoundException, CorruptedFileException {
        return new TaskList(storage.load());
    }

    /**
     * Returns the TaskList object containing the tasks.
     *
     * @return a TaskList object containing the tasks
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return ui.responseToCommand(input);
    }

}
