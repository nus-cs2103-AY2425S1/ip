package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code Command} interface represents a command that can be executed within the application.
 * Implementing classes define specific actions that the application can perform, such as adding tasks,
 * marking tasks as complete, or exiting the application.
 */
public interface Command {

    /**
     * Executes the command with the given context.
     *
     * @param storage The storage handler used for saving and retrieving tasks.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command. The specific exceptions
     *                        will depend on the command's implementation.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException;

}
