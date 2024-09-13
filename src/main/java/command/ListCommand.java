package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * Displays all the tasks currently in the task list.
     *
     * @param tasks   The TaskList object containing the current list of tasks.
     * @param ui      The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks to the storage file (not used in this command).
     * @throws BuddyException If there is an issue displaying the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        return ui.displayTasks(tasks.getTasks());
    }

    /**
     * Indicates whether this command should cause the program to exit.
     *
     * @return true, indicating that this command causes the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}