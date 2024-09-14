package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;
import darkpool.gui.Gui;


/**
 * Represents a command to unmark a task in the task list as not completed.
 */
public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {

        if (index < 0 || index >= taskList.getSize()) {
            throw new DarkpoolException("do you know how to count? the task number is out of range");
        }

        return gui.unmark(taskList.unmarkTask(index));
    }

}
