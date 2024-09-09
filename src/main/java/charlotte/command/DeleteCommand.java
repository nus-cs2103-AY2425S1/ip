package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand instance with the specified index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the task list, updates the user interface,
     * and saves the updated task list.
     * <p>
     * This method checks if the index is valid, removes the task at the given index from the TaskList,
     * informs the user of the successful removal, and then saves the updated task list to storage.
     * </p>
     *
     * @param tasks The TaskList object from which the task will be deleted.
     * @param ui The Ui object used to communicate with the user.
     * @param storage The Storage object used to save the updated task list.
     * @throws CharlotteException If the index is out of bounds or if an error occurs
     *     while saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        if (index < 1 || index > tasks.getSize()) {
            throw new CharlotteException("Task number is invalid. Please try again");
        }
        Task deletedTask = tasks.deleteTask(index - 1);
        storage.saveTasks(tasks);
        return ui.showMessage("Noted. I've removed this task:\n " + deletedTask
                + "\n Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
