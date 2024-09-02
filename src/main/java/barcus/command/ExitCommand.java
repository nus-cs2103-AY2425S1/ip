package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;
import barcus.exception.BarcusException;

/**
 * Command to execute exit
 */
public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        ui.showGoodbye();
        storage.upload(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
