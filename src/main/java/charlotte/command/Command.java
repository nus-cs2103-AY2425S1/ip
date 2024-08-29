package charlotte.command;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException;

    public boolean isExit() {
        return isExit;
    }
}
