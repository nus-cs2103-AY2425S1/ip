package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

/**
 * Abstract class representing a command in the application.
 * This class defines the methods that all command subclasses must implement.
 */
public abstract class Command {
    /**
     * Execute the command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
