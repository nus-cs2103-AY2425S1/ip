package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

import java.io.IOException;

/**
 * Unmarks a task in the task list as incomplete.
 * <p>
 * The UnmarkCommand class is responsible for marking a task as not done based on the task number provided by the user.
 * It validates the input, ensures the task number is within bounds, unmarks the task as not done,
 * and then saves the updated list to storage.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a new UnmarkCommand with the specified input parts.
     *
     * @param inputParts The command input parts, where inputParts[1] contains the task number.
     * @throws VinegarException If the input is invalid or the task number is not a valid integer.
     */
    public UnmarkCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify which task to unmark.");
        try {
            index = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new VinegarException("Task number must be a valid integer.");
        }
    }

    /**
     * Executes the unmark command by marking the task as not done and updating the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for displaying messages to the user.
     * @param storage The Storage for saving the updated task list.
     * @throws VinegarException If the task number is invalid.
     * @throws IOException      If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new VinegarException("Invalid task number. Please ensure the task number is correct and try again.");
        }

        tasks.getTask(index).markAsNotDone();
        storage.save(tasks.getTasks());
        return ui.showUnmarked(tasks.getTask(index));
    }
}
