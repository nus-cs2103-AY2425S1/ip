package skibidi;

import storage.TaskStorage;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     * @throws SkibidiException If an error occurs while executing the command.
     */
    public abstract String execute(Ui ui, TaskStorage storage) throws SkibidiException;
}
