package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * Inherits from the Command class.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     * If the task list is empty, a DrBrownException is thrown with an appropriate message.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui object to display messages to the user.
     * @param storage The Storage object for saving and loading tasks (not used in this command).
     * @throws DrBrownException If the task list is empty.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! "
                    + "We can't go anywhere until you add something to the list!");
        }
        tasks.listOut(ui);
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
