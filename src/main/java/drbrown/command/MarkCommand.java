package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to mark a task as completed in the task list.
 * Inherits from the Command class.
 */
public class MarkCommand extends Command {

    private final int itemIndex;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as completed.
     *
     * @param itemIndex The index of the task to mark as completed.
     */
    public MarkCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the MarkCommand by setting the status of the task at the specified index to completed.
     * It displays the marked task and handles exceptions if the index is out of bounds.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui object to display messages to the user.
     * @param storage The Storage object for saving changes to the file (not used in this command).
     * @throws DrBrownException If the task index is invalid (out of bounds).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            tasks.markTask(itemIndex, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
