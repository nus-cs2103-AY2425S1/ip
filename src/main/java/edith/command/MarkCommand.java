package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.Task;
import edith.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 * The MarkCommand class is used to update the status of a task in the TaskList to indicate that it has been completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param index An int representing the index of the task in the TaskList to be marked as done.
     *              The index is zero-based, so the first task has an index of 0.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done.
     *
     * <p>This method will:
     * <ul>
     *     <li>Check if the index is within the valid range of the TaskList. If not, an EdithException is thrown.</li>
     *     <li>Retrieve the task at the specified index and mark it as done.</li>
     *     <li>Display a confirmation message to the user indicating that the task has been marked as done.</li>
     *     <li>Save the updated TaskList using the Storage object.</li>
     * </ul>
     *
     * @param tasks The TaskList containing the tasks where one needs to be marked as done.
     * @param ui The Ui used to display the confirmation message after marking the task as done.
     * @param storage The Storage used to save the updated task list to persistent storage.
     * @throws EdithException If the specified index is out of range or if there is an error in marking the task as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        if (index < 0 || index >= tasks.getNumOfTasks()) {
            throw new EdithException("Task " + index + " does not exist. Please enter a valid task number.");
        }

        Task taskToMark = tasks.getTask(index);
        taskToMark.markTaskDone();

        StringBuilder response = new StringBuilder();
        response.append("Alright, great job! I've marked task ").append(index + 1).append(" as done:\n");
        response.append(taskToMark.toString());

        try {
            storage.save(tasks.getListOfTasks());
        } catch (IOException e) {
            return "An error occurred while saving updated task list.";
        }

        return response.toString();
    }
}
