package darkpool.command;

import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.util.Ui;
import darkpool.util.DarkpoolException;

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
