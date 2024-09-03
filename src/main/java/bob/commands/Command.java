package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the command interface.
 */
public abstract class Command {

    public abstract String execute(TaskList task, Ui ui, Storage storage);

    public abstract boolean isExit();
}
