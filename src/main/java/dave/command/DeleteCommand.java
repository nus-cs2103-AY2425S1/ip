package dave.command;

import java.io.IOException;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents the command to delete a task from the task list.
 * This command removes the task from the list, provides feedback to the user, and saves the updated list to storage.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a {@code DeleteCommand} with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list.
     * It removes the task from the list, saves the updated list to the storage, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object to handle saving the updated task list.
     * @param ui      The {@code Ui} object to handle user interaction.
     * @return A {@code String} message confirming that the task has been deleted successfully,
     * @throws IOException If an input or output error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        // Adding assertions for safety checks
        assert tasks != null : "TaskList should not be null.";
        assert storage != null : "Storage should not be null.";
        assert ui != null : "Ui should not be null.";
        assert index >= 0 : "Task index should be non-negative.";

        try {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            String output = "Noted. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
            storage.saveFile(tasks);
            return output;
        } catch (IndexOutOfBoundsException e) {
            ui.showLine();
            return "Oh no! You have entered an invalid number. Please try again.";
        } catch (IOException e) {
            ui.showLine();
            return "An error occurred while saving the task to the file.";
        } catch (Exception e) {
            ui.showLine();
            return "An unexpected error occurred while deleting the task.";
        }
    }
}
