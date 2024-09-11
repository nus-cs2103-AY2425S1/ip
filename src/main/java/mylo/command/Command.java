package mylo.command;

import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.task.TaskList;
import mylo.ui.Tui;
import mylo.utils.exceptions.IllegalValueException;

/**
 * Represents an abstract command that can be executed in the application.
 * <p></p>
 * <p>Subclasses of this abstract class are required to implement the {@code execute} method,
 * which defines the behavior when the command is executed on a given task list and user interface.</p>
 * <p></p>
 * <p>The {@code Command} class also includes a {@code isExit} method to check if the command is an exit command,
 * which indicates when the application should terminate.</p>
 * <p></p>
 * <p>Common exceptions like {@code StorageOperationException}, {@code InsufficientInfoException},
 * {@code IllegalValueException}, and {@code IndexOutOfBoundsException} can be thrown during command execution
 * based on specific operations.</p>
 *
 * @author cweijin
 */
public abstract class Command {
    /**
     * Executes the command using the provided {@code TaskList} and {@code Tui}.
     * <p></p>
     * <p>This method should be implemented by subclasses to define the specific action
     * that the command should perform, such as adding or deleting tasks.</p>
     *
     * @param list The task list that the command operates on.
     * @param tui   The user interface used to display the result of the command execution.
     * @throws StorageOperationException If there is an issue with saving or loading tasks from storage.
     * @throws InsufficientInfoException If there is insufficient information to execute the command.
     * @throws IllegalValueException     If the command encounters invalid values or operations.
     * @throws IndexOutOfBoundsException If an invalid index is used when accessing the task list.
     */
    public abstract String execute(TaskList list, Tui tui) throws StorageOperationException, InsufficientInfoException,
            IllegalValueException, IndexOutOfBoundsException;

    /**
     * Checks if this command is an {@code ExitCommand}, which signals the application to terminate.
     *
     * @return {@code true} if this is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
