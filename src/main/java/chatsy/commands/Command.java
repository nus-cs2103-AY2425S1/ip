package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.exceptions.InvalidTaskIndexException;
import chatsy.exceptions.InvalidTaskStringException;

/**
 * Represents an abstract command that can be executed.
 * Commands modify the state of the {@link TaskManager} and may throw various exceptions
 * depending on the command's validity and the state of the tasks.
 */
public abstract class Command {

    /**
     * Executes the command with the given {@link TaskManager}.
     *
     * @param taskManager The task manager to be modified by the command.
     * @return A string response after executing the command.
     * @throws InvalidCommandException If the command is invalid.
     * @throws EmptyDescriptionException If the task description is empty.
     * @throws InvalidTaskStringException If the task string is invalid.
     * @throws InvalidTaskIndexException If the task index is invalid.
     */
    public abstract String execute(TaskManager taskManager) throws InvalidCommandException,
            EmptyDescriptionException, InvalidTaskStringException, InvalidTaskIndexException;

    /**
     * Indicates whether this command should cause the application to exit.
     * By default, commands do not cause the application to exit.
     *
     * @return {@code true} if the command should cause the application to exit,
     *     {@code false} otherwise.
     */
    public boolean shouldExit() {
        return false;
    }
}
