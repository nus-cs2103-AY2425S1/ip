package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;
import darkpool.gui.Gui;


public abstract class Command {

    public abstract String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException;

    public boolean isExit() {
        return false;
    }

}
