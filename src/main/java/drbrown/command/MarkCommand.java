package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 * This command sets the status of a task at a specified index in the TaskList to "completed"
 * and provides feedback to the user.
 */
public class MarkCommand extends Command {

    private final int itemIndex;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as completed.
     *
     * @param itemIndex The zero-based index of the task to mark as completed in the task list.
     */
    public MarkCommand(int itemIndex) {
        assert itemIndex < 0 : "Item index should not be negative";
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the MarkCommand by setting the status of the task at the specified index to completed.
     * If the operation is successful, it displays the marked task using the Ui object.
     * If the index is invalid (out of bounds), it throws a DrBrownException with an appropriate error message.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui object to display messages to the user.
     * @param storage The Storage object for saving changes to the file (not used in this command).
     * @return A string message confirming the task has been marked as completed.
     * @throws DrBrownException If the task index is invalid (out of bounds).
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        try {
            return tasks.markTask(itemIndex, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That's not how you calculate "
                    + "time travel - you're off by a few gigawatts!");
        }
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
