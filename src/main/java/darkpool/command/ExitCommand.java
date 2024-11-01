package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to exit the program.
 */
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
