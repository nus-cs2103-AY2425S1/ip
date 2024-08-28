package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TickException;

    public abstract boolean isExit();
}
