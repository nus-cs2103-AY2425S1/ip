package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.task.Task;
import vinegar.ui.Ui;

import java.io.IOException;

/**
 * Deletes a task or all tasks from the task list.
 * <p>
 * The DeleteCommand class is responsible for removing a task from the task list
 * based on the task number provided by the user, or all tasks if the "all" command is used.
 * It validates the input, ensures the task number is within bounds, deletes the task(s),
 * and then saves the updated list to storage.
 */
public class DeleteCommand extends Command {
    private int index = -1;  // -1 indicates the "all" command

    /**
     * Constructs a new DeleteCommand with the specified input parts.
     *
     * @param inputParts The command input parts, where inputParts[1] contains the task number or "all".
     * @throws VinegarException If the input is invalid or the task number is not a valid integer or "all".
     */
    public DeleteCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify which task to delete or use 'all' to delete all tasks.");

        // Check if the user wants to delete all tasks
        if (inputParts[1].equalsIgnoreCase("all")) {
            index = -1; // Special flag for deleting all tasks
        } else {
            // Try parsing the task number
            try {
                index = Integer.parseInt(inputParts[1]) - 1;
            } catch (NumberFormatException e) {
                throw new VinegarException("Task number must be a valid integer or 'all' to delete all tasks.");
            }
        }
    }

    /**
     * Executes the delete command by removing the specified task or all tasks from the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for displaying messages to the user.
     * @param storage The Storage for saving the updated task list.
     * @throws VinegarException If the task number is invalid.
     * @throws IOException      If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        if (index == -1) {
            // Handle the case where "all" is passed
            tasks.clear(); // Clear all tasks
            storage.save(tasks.getTasks()); // Save the empty task list
            return ui.showDeletedAll(); // Show a message confirming all tasks were deleted
        } else {
            // Handle deleting a single task
            if (index < 0 || index >= tasks.size()) {
                throw new VinegarException("Invalid task number. Please ensure the task number is correct and try again.");
            }

            Task taskToRemove = tasks.removeTask(index);
            storage.save(tasks.getTasks());
            return ui.showDeleted(taskToRemove, tasks.size());
        }
    }
}
