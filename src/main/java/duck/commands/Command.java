package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * The Command class serves as the abstract base class for all command types in the Duck application.
 * It provides a framework for executing specific commands and determining whether a command signifies
 * an exit operation.
 */
public abstract class Command {

    /** The message associated with the command. */
    protected String message;

    /**
     * Constructs a Command with the specified message.
     *
     * @param message The message associated with the command.
     */
    public Command(String message) {
        this.message = message;
    }

    /**
     * Executes the command using the provided TaskList, Storage, and Ui instances.
     * This method must be implemented by subclasses to define specific command behavior.
     *
     * @param tasks The list of tasks to be manipulated by the command.
     * @param storage The storage system for saving and loading tasks.
     * @param ui The user interface for displaying messages to the user.
     * @throws DuckException If an error occurs during the execution of the command.
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        // assertions are done in the abstract class so that subclasses will not need to check for them again.
        assert tasks != null;
        assert storage != null;
        assert ui != null;
    }

    /**
     * Determines whether the command signifies an exit operation.
     * Subclasses must implement this method to return true if the command indicates that the application
     * should exit.
     *
     * @return true if the command signifies an exit operation; false otherwise.
     */
    public abstract boolean isExit();
}
