package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.LunaException;

/**
 * Represents a command from the user to be executed.
 */
public abstract class Command {

    /**
     * Executes the given command.
     *
     * @param tasks The current list of tasks.
     * @param storage Storage used to save tasks.
     * @throws LunaException If the command to be executed is invalid.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws LunaException;
}
