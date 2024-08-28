package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException;

    public abstract boolean isExit();
}
