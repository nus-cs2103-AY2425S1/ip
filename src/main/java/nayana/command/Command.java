package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;

/**
 * Represents an abstract command.
 * This class serves as a base for all commands in the application.
 * Concrete implementations of this class must provide implementations for
 * executing the command and determining if the command is an exit command.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to be manipulated by the command.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks.
     * @throws NayanaException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException;

    /**
     * Determines if this command is an exit command.
     *
     * @return true if this command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}

