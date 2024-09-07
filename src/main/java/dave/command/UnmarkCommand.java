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
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     * It updates the task's status, saves the updated task list to the storage, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object to handle saving the updated task list.
     * @param ui      The {@code Ui} object to handle user interaction.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(task);
            Storage.saveFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showLine();
            System.out.println("Oh no! You have entered an invalid number. Please try again.");
        } catch (IOException e) {
            ui.showLine();
            System.out.println("An error occurred while saving the task to the file.");
        } catch (Exception e) {
            ui.showLine();
            System.out.println("An unexpected error occurred while marking the task.");
        }
    }
}
