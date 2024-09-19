package silverwolf.command;

import silverwolf.exception.SilverWolfException;
import silverwolf.storage.Storage;
import silverwolf.task.Task;
import silverwolf.task.TaskList;
import silverwolf.ui.Ui;


/**
 * The AddCommand class represents a command to add a new task to the task list.
 * It extends the Command class and encapsulates the logic for adding tasks
 * such as Todos, Deadlines, and Events to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the TaskList, saving the updated list to storage,
     * and providing feedback to the user through the UI.
     *
     * @param tasks   The TaskList object that manages the list of tasks.
     * @param ui      The Ui object that handles user interaction.
     * @param storage The Storage object responsible for saving and loading tasks from the file.
     * @throws SilverWolfException If there is an error during the execution of the command (e.g., saving to storage).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SilverWolfException {
        tasks.addTask(task); // Add the task to the TaskList
        storage.save(tasks.getTasks()); // Save the updated task list to storage
        String outputToGui = "Got it. I've added this task:\n";
        outputToGui = outputToGui + task;
        outputToGui = outputToGui + "\nNow you have " + tasks.getSize() + " tasks in the list.";
        feedback = outputToGui;
    }
}
