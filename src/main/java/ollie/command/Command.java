package ollie.command;

import ollie.*;
import ollie.exception.*;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() { return isExit; }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException;
}
