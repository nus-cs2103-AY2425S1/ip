package tick.commands;

import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * List all tasks in the task list.
     *
     * @param tasks Task list containing all tasks to be listed.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save and load tasks from the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null.";
        ui.showList(tasks);
    }

    /**
     * Returns false as the command is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
