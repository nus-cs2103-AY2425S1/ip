package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command to execute exit
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        ui.showGoodbye();
        output = "Alright, good talk!";
        storage.upload(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
