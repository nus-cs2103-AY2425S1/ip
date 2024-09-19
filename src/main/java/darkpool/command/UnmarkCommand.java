package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        OutOfRange.check(index, taskList);
        return gui.unmark(taskList.unmarkTask(index));
    }

}
