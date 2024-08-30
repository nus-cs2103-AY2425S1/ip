package nether.command;

import nether.NetherException;
import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

/**
 * Represents an abstract command in the Nether application.
 *
 * The {@code Command} class provides a blueprint for all command types used in the application.
 * It defines the basic structure and behavior for executing commands and handling termination.
 *
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface, and storage.
     *
     * This method must be implemented by subclasses to define the specific behavior of the command.
     *
     * @param tasks The {@code TaskList} to be modified or queried by the command.
     * @param ui The {@code Ui} instance used to interact with the user.
     * @param storage The {@code Storage} instance used to persist changes.
     * @throws NetherException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NetherException;

    /**
     * Determines if the command indicates the end of the application.
     *
     * The default implementation returns {@code false}, indicating that the command does not terminate the application.
     * Subclasses may override this method to provide specific termination behavior.
     *
     * @return {@code true} if the command signals an exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
