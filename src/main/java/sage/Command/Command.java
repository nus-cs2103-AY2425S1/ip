package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Ui;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SageException;

    public boolean isExit() {
        return isExit;
    }
}