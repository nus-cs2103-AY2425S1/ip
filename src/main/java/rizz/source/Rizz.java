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
            String str = "";
            if (command instanceof SaveableCommand) {
                this.saveSnapshot();
                str = command.execute(tasks);
                storage.saveTasks(tasks);
            } else if (command instanceof UndoCommand) {
                this.undo();
                str = command.execute(tasks);
            } else {
                str = command.execute(tasks);
            }
            return str;
        } else {
            return "Oops! I'm unable to process that command! Please try again!";
        }
    }

    /**
     * Reverts the current task list to the previous state
     * The method restores the task list from the most recent snapshot in the history stack,
     * effectively "undoing" the last change made to the task list. The previous state is removed
     * from the history stack, and the task list is updated to that state. If the history stack is empty,
     * no changes will be made.
     *
     * @throws IOException If an I/O error occurs while saving the reverted task list to storage.
     */
    //@@author ChatGPT -> Create Deep Copy and stack history of TaskList to store previous state of file
    public void undo() throws IOException {
        if (!historyStack.isEmpty()) {
            tasks = historyStack.pop();
            storage.saveTasks(tasks);
        }
    }

    /**
     * Save a snapshot of the current task list to the history stack.
     */
    //@@author ChatGPT -> Create Deep Copy and stack history of TaskList to store previous state of file
    private void saveSnapshot() {
        historyStack.push(new TaskList(this.tasks));
    }
}
