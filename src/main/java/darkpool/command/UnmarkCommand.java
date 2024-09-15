package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;
import darkpool.gui.Gui;

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
