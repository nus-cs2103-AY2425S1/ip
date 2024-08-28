package ollie.command;

import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() { return isExit; }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException;
}
