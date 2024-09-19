package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command for unknown input
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        throw new BarcusException("command not found D:");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
