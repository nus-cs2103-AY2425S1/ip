package dave.command;

import java.io.IOException;
import dave.task.Task;
import dave.task.TaskList;
import dave.storage.Storage;
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
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
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
