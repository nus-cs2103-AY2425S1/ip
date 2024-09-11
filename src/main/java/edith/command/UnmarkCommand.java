package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as not done.
 * The UnmarkCommand class is used to update the status of a task in the TaskList to indicate that it is no longer completed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to be marked as not done.
     *
     * @param index An int representing the index of the task in the TaskList to be marked as not done.
     *              The index is zero-based, so the first task has an index of 0.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by marking the specified task as not done.
     *
     * <p>This method will:
     * <ul>
     *     <li>Check if the index is within the valid range of the TaskList. If not, an EdithException is thrown.</li>
     *     <li>Retrieve the task at the specified index and mark it as not done.</li>
     *     <li>Display a confirmation message to the user indicating that the task has been marked as not done.</li>
     *     <li>Save the updated TaskList using the Storage object.</li>
     * </ul>
     *
     * @param tasks The TaskList containing the tasks where one needs to be marked as not done.
     * @param ui The Ui used to display the confirmation message after marking the task as not done.
     * @param storage The Storage used to save the updated task list to persistent storage.
     * @throws EdithException If the specified index is out of range or if there is an error in marking the task as not done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Task " + index + " does not exist. Please enter a valid task number.");
        }

        Task taskToUnmark = tasks.getTask(index);
        taskToUnmark.markTaskUndone();

        StringBuilder response = new StringBuilder();
        response.append("Sure, I've marked task ").append(index + 1).append(" as not done yet:\n");
        response.append(taskToUnmark.toString());

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            return "An error occurred while saving updated task list.";
        }

        return response.toString();
    }
}
