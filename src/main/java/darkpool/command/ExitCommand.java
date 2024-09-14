package darkpool.command;

import darkpool.gui.Gui;
import darkpool.tasklist.TaskList;
import darkpool.DarkpoolException;
import darkpool.storage.Storage;

public class ExitCommand extends Command {


    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        storage.saveData(taskList);
        return gui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
