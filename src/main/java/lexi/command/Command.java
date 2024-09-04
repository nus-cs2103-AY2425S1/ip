package lexi.command;

import lexi.exception.LexiException;
import lexi.storage.Storage;
import lexi.task.TaskList;
import lexi.ui.Ui;

/**
 * Represents an abstract command in the Lexi application.
 * This class provides a template for specific command types and includes methods to execute the command,
 * check if the command is an exit command, and set the exit status.
 */
public abstract class Command {

    /**
     * Executes the command.
     * This method must be implemented by subclasses to define the specific behavior of the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage to update the task list.
     * @throws LexiException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException;

    /**
     * Returns the name of the command.
     * This method must be implemented by subclasses to return the specific command name.
     *
     * @return The name of the command.
     */
    public abstract String getCommandName();
    public abstract String getString();
}
