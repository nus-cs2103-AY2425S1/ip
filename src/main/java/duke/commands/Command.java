package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command in the DailyTasks application.
 * <p>
 * Commands are responsible for performing specific actions that manipulate
 * the task list, interact with the user interface, or modify the storage.
 * Subclasses must implement the {@link #execute(TaskList, Ui, Storage)} method
 * to define specific behavior.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     * <p>
     * Subclasses of {@code Command} should override this method to provide
     * specific behavior, such as adding, removing, or updating tasks.
     * </p>
     *
     * @param taskList The list of tasks to be manipulated by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to read from or write to the file system.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException;
}
