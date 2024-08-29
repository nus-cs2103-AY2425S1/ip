package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

/**
 * Base class for all commands.
 */
public abstract class Command {

    public abstract void execute(TaskList task, Ui ui, Storage storage);

    public abstract boolean isExit();
}
