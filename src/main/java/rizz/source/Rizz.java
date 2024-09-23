package rizz.source;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import rizz.command.Command;
import rizz.command.Parser;
import rizz.command.SaveableCommand;
import rizz.command.UndoCommand;
import rizz.task.Storage;

/**
 * The main class for the Rizz application, which manages tasks.
 * The Rizz class handles the interaction between the user, tasks, and storage.
 */
public class Rizz {
    private final Storage storage;
    private final Deque<TaskList> historyStack;
    private TaskList tasks;


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
        this.historyStack = new LinkedList<>();

    }

    /**
     * Runs the main loop of the Rizz application.
     * Greets the user, processes user commands, executes them, and saves tasks after each command.
     *
     * @throws IOException If an I/O error occurs when saving tasks to the file.
     */
    public String getResponse(String input) throws IOException {
        Command command = Parser.parseCommand(input, tasks);
        if (command != null) {
            if (command instanceof SaveableCommand) {
                this.saveSnapshot();
                storage.saveTasks(tasks);
            } else if (command instanceof UndoCommand) {
                this.undo();
            }
            return command.execute(tasks);
        } else {
            return "-1";
        }
    }

    public void undo() throws IOException {
        if (!historyStack.isEmpty()) {
            tasks = historyStack.pop();
            storage.saveTasks(tasks);
        }
    }

    /**
     * Save a snapshot of the current task list to the history stack.
     */
    private void saveSnapshot() {
        historyStack.push(new TaskList(this.tasks));
    }
}
