package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;

/**
 * Represents a command from the user to be executed.
 */
public interface Command {

    /**
     * Executes the given command.
     *
     * @param tasks   The current list of tasks.
     * @param storage Storage used to save tasks.
     * @return The response to be shown to the user.
     * @throws LunaException If the command to be executed is invalid.
     */
    String execute(TaskList tasks, Storage storage) throws LunaException;

    String undo(TaskList tasks, Storage storage) throws LunaException;

    Command getPreviousCommand();
}
