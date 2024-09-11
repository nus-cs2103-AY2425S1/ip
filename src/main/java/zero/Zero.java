package zero;

import zero.exception.ZeroException;
import zero.task.TaskList;
import zero.ui.Ui;
import zero.util.Parser;
import zero.util.Storage;

/**
 * The {@code Zero} class is the main class for the Zero application.
 * It handles the initialisation of the necessary components and manages the application's main loop.
 */
public class Zero {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@code Zero} object.
     * Initialises the UI, storage, and task list components. If there is an error loading the tasks from the file,
     * a new empty task list is created, and the error is displayed via the UI.
     *
     * @throws ZeroException
     */
    public Zero() {
        String filePath = "data/zero.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ZeroException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the Zero application.
     * Continuously reads user commands, processes them using the {@code Parser}, updates the task list,
     * and saves the tasks to the file. The loop terminates when the user issues a command to exit.
     */


    public String getResponse(String input) {
        try {
            String output = Parser.parseCommand(input, tasks, ui);
            storage.save(tasks);
            return output;
        } catch (ZeroException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * The main entry point of the Zero application.
     * Initialises the file path to the stored information
     *
     * @param args The first line can be used to specify the file path of the storage
     */
    public static void main(String[] args) {
        new Zero();
    }
}
