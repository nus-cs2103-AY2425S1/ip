package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command displays all tasks currently stored in the TaskList to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     * If the task list is empty, a DrBrownException is thrown with an appropriate message.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The Ui object to display messages to the user.
     * @param storage The Storage object for saving and loading tasks (not used in this command).
     * @return A string message containing the list of all tasks or an appropriate message if the list is empty.
     * @throws DrBrownException If the task list is empty.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! "
                    + "We can't go anywhere until you add something to the list!");
        }
        return tasks.listOut(ui);
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
