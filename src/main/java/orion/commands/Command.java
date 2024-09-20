package orion.commands;

import orion.exceptions.OrionException;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents an abstract command in the system.
 * <p>
 * A command encapsulates an action that can be executed, such as adding or
 * removing a task. Commands can be executed against a {@link TaskList} and
 * {@link Storage}. The {@code isExit} flag determines if
 * the command should terminate the current process.
 * </p>
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Constructs a {@code Command} with the specified exit flag.
     *
     * @param isExit a boolean indicating if the command should terminate
     *               the current process (true) or continue (false)
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     * <p>
     * The specific behavior of the command is defined in subclasses. This
     * method interacts with the given {@link TaskList} and {@link Storage}
     * to perform the command's action.
     * </p>
     *
     * @param tasks  the {@link TaskList} to perform actions on
     * @param storage the {@link Storage} for managing data
     * @return The response String upon completion of the command
     * @throws OrionException if an error occurs during execution
     */
    public abstract String execute(TaskList tasks, Storage storage) throws OrionException;

    /**
     * Checks if the command signals an exit.
     *
     * @return {@code true} if the command should terminate the current process,
     *         {@code false} otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }
}
