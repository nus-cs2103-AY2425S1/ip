package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        OutOfRange.check(index, taskList);
        String delString = taskList.deleteTask(index);
        return gui.delete(delString);
    }

}
