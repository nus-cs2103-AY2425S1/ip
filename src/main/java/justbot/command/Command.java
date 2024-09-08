package justbot.command;

import justbot.exception.JustbotException;
import justbot.storage.Storage;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents an abstract command in the Justbot application.
 * Subclasses of Command define specific actions that can be performed on the task list.
 */
public abstract class Command {

    /**
     * Executes the command, performing the specific action on the task list.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI instance used to interact with the user.
     * @param storage The Storage instance used to save or load tasks.
     * @throws JustbotException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JustbotException;


    /**
     * Indicates whether this command should terminate the application.
     *
     * @return {@code true} if the application should exit after this command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the task associated with this command.
     * This method is abstract and must be implemented by subclasses to return the relevant task.
     *
     * @return The task associated with this command.
     */
    public abstract Task getTask();
}
