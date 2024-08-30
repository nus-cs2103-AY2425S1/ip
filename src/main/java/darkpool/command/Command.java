package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;
import darkpool.util.DarkpoolException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException;

    public boolean isExit() {
        return false;
    }

}
