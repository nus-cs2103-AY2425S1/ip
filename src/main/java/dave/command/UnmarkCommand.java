package dave.command;

import java.io.IOException;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents the command to unmark a task as not done in the task list.
 * This command modifies the task's status to indicate it is not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be unmarked as not done (1-based index).
     */
    public UnmarkCommand(int taskIndex) {
        assert taskIndex > 0 : "Task index must be positive.";
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     * It updates the task's status, saves the updated task list to the storage, and returns feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object to handle saving the updated task list.
     * @param ui      The {@code Ui} object to handle user interaction.
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "Task list should not be null.";
        assert storage != null : "Storage should not be null.";

        try {
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsNotDone();
            Storage.saveFile(tasks);
            return "Ok, I've marked this task as not done yet:\n" + task;
        } catch (IndexOutOfBoundsException e) {
            return "Oh no! You have entered an invalid number. Please try again.";
        } catch (IOException e) {
            return "An error occurred while saving the task to the file.";
        } catch (Exception e) {
            return "An unexpected error occurred while marking the task.";
        }
    }
}
