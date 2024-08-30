package Darkpool.Command;

import Darkpool.util.Storage;
import Darkpool.util.TaskList;
import Darkpool.util.Ui;
import Darkpool.util.DarkpoolException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException {
        ui.goodbye();
        storage.saveData(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
