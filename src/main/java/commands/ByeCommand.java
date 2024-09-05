package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code ByeCommand} class represents a command that, when executed, signals the termination
 * of the application. It implements the {@code Command} interface.
 */
public class ByeCommand implements Command {

    /**
     * Executes the Bye command. This implementation does not modify any data or interact with
     * storage, task list, or UI, as the command simply signals the end of the application.
     *
     * @param storage The storage handler used for saving and retrieving tasks. Not used in this implementation.
     * @param tasks   The list of tasks currently in memory. Not used in this implementation.
     * @param ui      The UI handler used for interacting with the user. Not used in this implementation.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showExitMessage();
    }

}
