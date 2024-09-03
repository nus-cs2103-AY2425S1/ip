package astra.command;

import astra.AstraException;
import astra.Storage;
import astra.TaskList;
import astra.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Executes the command in CLI mode.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws AstraException If an error occurs.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException;

    /**
     * Executes the command in GUI mode.
     *
     * @param tasks The list of tasks.
     * @param storage The storage.
     * @return The response from the command.
     * @throws AstraException If an error occurs.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws AstraException;

    public abstract boolean isExit();
}
