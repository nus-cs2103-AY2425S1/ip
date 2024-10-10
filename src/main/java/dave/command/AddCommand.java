package dave.command;

import java.io.IOException;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

/**
 * Represents the command to add a new task to the task list.
 * This command adds the task, provides feedback to the user, and saves the updated list to storage.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list.
     * It adds the task to the list, saves the updated list to the storage, and provides feedback to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object to handle saving the updated task list.
     * @param ui      The {@code Ui} object to handle user interaction.
     * @return A {@code String} message confirming that the task has been added successfully.
     * @throws IOException If an input or output error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        // Adding assertions for safety checks
        assert task != null : "Task should not be null.";
        assert tasks != null : "TaskList should not be null.";
        assert storage != null : "Storage should not be null.";
        assert ui != null : "Ui should not be null.";

        try {
            tasks.addTask(task);
            String output = "Got it. I've added this task: \n"
                    + task + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
            storage.amendFile(task); // Ensure amendFile is accessed via the correct instance
            return output;
        } catch (Exception e) {
            return "An unexpected error occurred while adding the task.";
        }
    }
}
