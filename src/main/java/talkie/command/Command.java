package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieException;
import talkie.task.TaskList;

/**
 * Represents an abstract command in the Talkie application.
 * Commands are actions that the user can perform, such as adding tasks, deleting tasks, or exiting the application.
 * Subclasses of {@code Command} must implement the {@code execute} method to define the specific action.
 * Subclasses must also implement the {@code isExit} method to define whether the command will exit the program.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     * This method must be implemented by all subclasses to define the specific behavior of the command.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to interact with the user.
     * @param storage The storage component used to save or load task data.
     * @throws TalkieException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieException;

    /**
     * Indicates whether the command will terminate the application.
     * Subclasses should override this method to return {@code true} if the command causes the application to exit.
     *
     * @return {@code true} if the command ends the program, {@code false} otherwise.
     */
    public abstract boolean isExit();
}
