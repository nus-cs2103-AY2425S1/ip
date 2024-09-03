package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieException;
import talkie.task.TaskList;

/**
 * Represents an abstract command in the Talkie application.
 * <p>
 * Commands are actions that the user can perform, such as adding tasks, deleting tasks, or exiting the application.
 * Subclasses of {@code Command} must implement the {@code execute} method to define the specific action the command
 * performs. Additionally, subclasses must implement the {@code isExit} method to define whether the command will
 * cause the program to exit.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     * <p>
     * This method is abstract and must be implemented by all subclasses to define the specific behavior of the command.
     * The method interacts with the task list, the user interface, and the storage system,
     * and may throw a {@code TalkieException} if an error occurs during execution.
     * </p>
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to interact with the user.
     * @param storage The storage component used to save or load task data.
     * @return A string representing the result of the command's execution, typically a message to be
     *         displayed to the user.
     * @throws TalkieException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws TalkieException;

    /**
     * Indicates whether the command will terminate the application.
     * <p>
     * Subclasses should override this method to return {@code true} if the command causes the application to exit.
     * By default, commands are assumed not to terminate the application unless explicitly overridden.
     * </p>
     *
     * @return {@code true} if the command ends the program, {@code false} otherwise.
     */
    public abstract boolean isExit();
}
