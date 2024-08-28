package command;

import exception.DudeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    /**
     * Lists all the tasks in the task list.
     *
     * @param tasks The task list to be listed.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException If there is an error listing the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
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
