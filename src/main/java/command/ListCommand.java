package command;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to list all the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Lists all the tasks in the task list.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DynamikeException If there is an error listing the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DynamikeException {
        assert tasks != null : "Task list should not be null";
        ui.showList(tasks);
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
