package barcus.command;

import barcus.tasklist.TaskList;
import barcus.ui.Ui;
import barcus.storage.Storage;
import barcus.exception.BarcusException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException;
    public abstract boolean isExit();
}
