package Command;

import List.TaskList;
import Sage.SageException;
import Sage.Storage;
import Sage.Ui;

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