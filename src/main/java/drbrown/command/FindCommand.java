package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to find tasks that match a given keyword.
 * This command searches through the task list for tasks that contain the specified keyword
 * and provides the matching tasks to the user.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Find keyword should not be null";
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks in the provided TaskList that contain the keyword.
     * If matching tasks are found, they are displayed using the Ui object.
     * If no tasks are found or the list is empty, appropriate messages are shown to the user.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for user interactions and displaying messages.
     * @param storage The Storage object for saving/loading tasks (not used in this command).
     * @return A string message displaying the tasks that match the keyword or a message indicating no matches.
     * @throws DrBrownException If there are no tasks in the list to search from.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! We can't go "
                    + "anywhere until you add something to the list!");
        }
        return tasks.findMatching(this.keyword, ui);
    }

    /**
     * Indicates whether the command causes the application to exit.
     *
     * @return false, since FindCommand does not cause the application to exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
