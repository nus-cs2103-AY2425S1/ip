package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand instance with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "Task should not be null";
        this.task = task;
    }

    /**
     * Returns the task associated with this AddCommand.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        assert task != null : "Task should not be null";
        return this.task;
    }

    /**
     * Executes the command to add the task to the task list, updates the user interface,
     * and saves the updated task list.
     * <p>
     * This method adds the task to the TaskList, informs the user of the successful addition,
     * and then saves the updated task list to storage.
     * </p>
     *
     * @param tasks The TaskList object where the task will be added.
     * @param ui The Ui object used to communicate with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws CharlotteException If an error occurs while saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        tasks.addTask(task);
        assert tasks.getSize() > 0 : "Task list size should increase after adding a task";
        storage.saveTasks(tasks);
        return ui.showMessage("Got it. I've added this task:\n  " + task
                + "\n Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
