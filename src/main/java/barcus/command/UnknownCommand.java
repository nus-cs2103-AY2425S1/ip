package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command for unknown input
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {

        //ui.showError("command not found D:");
        throw new BarcusException("command not found D:");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
