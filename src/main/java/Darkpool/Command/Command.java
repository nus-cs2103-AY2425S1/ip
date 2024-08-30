package Darkpool.Command;

import Darkpool.util.Storage;
import Darkpool.util.TaskList;
import Darkpool.util.Ui;
import Darkpool.util.DarkpoolException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException;

    public boolean isExit() {
        return false;
    }

}
