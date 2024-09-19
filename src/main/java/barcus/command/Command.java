package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Abstract command class
 */
public abstract class Command {
    protected String output;

    public abstract void execute(TaskList tasks, Storage storage) throws BarcusException;
    public abstract boolean isExit();
    public String getString() {
        assert !output.isEmpty();
        return output;
    };
}
