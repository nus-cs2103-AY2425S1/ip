package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task at a specified index from the TaskList and provides
 * feedback to the user through the UI.
 */
public class DeleteCommand extends Command {

    private final int itemIndex;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param itemIndex The zero-based index of the task to delete in the task list.
     */
    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the DeleteCommand by removing a task from the provided TaskList at the specified index.
     * If successful, it displays a confirmation message for the deleted task and the updated task count.
     * If the index is invalid (out of bounds), it throws a DrBrownException with an appropriate error message.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object to handle user interactions and display messages.
     * @param storage The Storage object for saving/loading tasks (not used in this command).
     * @return A string message confirming the deletion of the task and showing the updated task count.
     * @throws DrBrownException If the index is out of bounds of the task list.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            return tasks.removeItem(itemIndex, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That's not how you calculate time "
                    + "travel - you're off by a few gigawatts!");
        }
    }

    /**
     * Indicates whether the command causes the application to exit.
     *
     * @return false, since DeleteCommand does not cause the application to exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
