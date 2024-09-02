package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents an abstract command in the application.
 * <p>
 * This class serves as the base class for all specific commands. It defines the common interface
 * that all commands must implement.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command.
     * <p>
     * The implementation of this method will define the specific behavior of the command,
     * such as modifying the task list, interacting with the user interface, or handling storage.
     * </p>
     *
     * @param taskList The list of tasks managed by the application.
     * @param ui The user interface that handles output and user interactions.
     * @param storage The storage that handles data persistence.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Determines if the command signifies an exit operation.
     * <p>
     * By default, this method returns false, indicating that the command does not signify
     * an exit operation. Subclasses can override this method to return true if the command
     * should trigger an exit.
     * </p>
     *
     * @return true if the command signifies an exit operation; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
