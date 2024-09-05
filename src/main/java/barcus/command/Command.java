package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Abstract command class
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException;
    public abstract boolean isExit();
}
