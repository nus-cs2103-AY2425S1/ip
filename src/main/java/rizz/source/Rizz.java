package rizz.source;
import java.io.IOException;
import rizz.command.Command;
import rizz.command.Parser;
import rizz.task.Storage;

/**
 * The main class for the Rizz application, which manages tasks.
 * The Rizz class handles the interaction between the user, tasks, and storage.
 */
public class Rizz {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new Rizz application with the specified file path for task storage.
     * Initializes the storage, task list, and user interface.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     * @throws IOException If an I/O error occurs when loading tasks from the file.
     */
    public Rizz(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the main loop of the Rizz application.
     * Greets the user, processes user commands, executes them, and saves tasks after each command.
     *
     * @throws IOException If an I/O error occurs when saving tasks to the file.
     */
    public String getResponse(String input) throws IOException {
        Command command = Parser.parseCommand(input);
        if (command != null) {
            String str = command.execute(tasks);
            storage.saveTasks(tasks);
            return str;
        } else {
            return "-1";
        }
    }
}
