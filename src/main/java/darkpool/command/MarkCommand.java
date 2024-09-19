package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        OutOfRange.check(index, taskList);
        return gui.mark(taskList.markTask(index));
    }

}
