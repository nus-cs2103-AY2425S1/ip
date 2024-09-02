package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * The DeleteCommand class removes a specific task identified by its index from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the task at the specified index from the task list
     * and updating the storage with the new task list.
     *
     * <p>This method will:
     * <ul>
     *     <li>Check if the provided index is valid. If not, an EdithException is thrown.</li>
     *     <li>Remove the task at the specified index from the task list.</li>
     *     <li>Display a message confirming the removal and the updated number of tasks.</li>
     *     <li>Save the updated task list using the provided Storage.</li>
     * </ul>
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param ui The Ui used to display messages to the user.
     * @param storage The Storage used to save the updated task list.
     * @throws EdithException If the index is invalid or the task to delete does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Edith.task.Task " + index + " does not exist. Please enter a valid Edith.task number.");
        }

        Task taskToDelete = tasks.getTask(index);
        tasks.deleteTask(index);

        ui.showIndentedMessage("Certainly. I've removed this Edith.task:");
        ui.showIndentedMessage(taskToDelete.toString());
        ui.showIndentedMessage("There are now " + tasks.getNumOfTasks() + " tasks in your list.");
        ui.showLineBreak();

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated Edith.task list.");
        }
    }
}
