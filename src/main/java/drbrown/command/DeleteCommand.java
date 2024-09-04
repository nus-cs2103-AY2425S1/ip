package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to delete a task from the task list.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    private final int itemIndex;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param itemIndex The index of the task to delete in the task list.
     */
    public DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Executes the DeleteCommand, removing a task from the provided TaskList at the specified index.
     * If successful, displays confirmation and the remaining task count using the Ui object.
     * If the index is invalid, throws a DrBrownException with an appropriate error message.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for user interactions.
     * @param storage The Storage object for saving/loading tasks (not used in this command).
     * @throws DrBrownException If the index is out of bounds of the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            tasks.removeItem(itemIndex, ui);
            ui.showEnd();
            ui.showCount(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    /**
     * Indicates whether the command causes the application to exit.
     *
     * @return false, since DeleteCommand does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
