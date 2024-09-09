package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command that the user wants to execute.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface that interacts with the user.
     * @param storage The storage to be updated.
     * @throws SpikeException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Returns the type of the command.
     *
     * @return The type of the command.
     */
    public abstract String getCommandType();
}


