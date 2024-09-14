package darkpool.command;

import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.tasklist.TaskList;
import darkpool.gui.Gui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Gui gui, Storage storage) throws DarkpoolException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DarkpoolException("do you know how to count? the task number is out of range");
        }
        String delString = tasks.deleteTask(index);
        return gui.delete(delString);
    }

}
