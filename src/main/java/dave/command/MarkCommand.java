package dave.command;

import java.io.IOException;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents the command to mark a task as done in the task list.
 * This command modifies the task's status to indicate completion.
 */
public class MarkCommand extends Command {

    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as done (1-based index).
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     * It updates the task's status, saves the updated task list to the storage, and returns feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object to handle saving the updated task list.
     * @param ui      The {@code Ui} object to handle user interaction.
     * @return A string message indicating the result of the operation.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsDone();
            Storage.saveFile(tasks);
            return "Nice! I've marked this task as done:\n" + task;
        } catch (IndexOutOfBoundsException e) {
            return "Oh no! You have entered an invalid number. Please try again.";
        } catch (IOException e) {
            return "An error occurred while saving the task to the file.";
        } catch (Exception e) {
            return "An unexpected error occurred while marking the task.";
        }
    }
}
