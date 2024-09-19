package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to execute exit
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        output = "Alright, good talk!";
        storage.upload(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
