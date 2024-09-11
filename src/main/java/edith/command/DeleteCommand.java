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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Task " + index + " does not exist. Please enter a valid task number.");
        }

        Task taskToDelete = tasks.getTask(index);
        tasks.deleteTask(index);

        StringBuilder response = new StringBuilder();
        response.append("Certainly. I've removed this task:\n");
        response.append(taskToDelete.toString()).append("\n");
        response.append("There are now ").append(tasks.getNumOfTasks()).append(" tasks in your list.");

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            return "An error occurred while saving updated task list.";
        }

        return response.toString();
    }
}
