package strand.command;

import strand.exception.StrandException;

/**
 * The {@code Command} class serves as an abstract base class for all commands
 * in the Strand application.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The {@code TaskList} containing the tasks to be operated on.
     * @param ui      The {@code Ui} object used for interacting with the user.
     * @param storage The {@code Storage} object used for saving task data.
     * @throws StrandException If there is an error during command execution.
     */
    public abstract String execute(strand.TaskList tasks, strand.Ui ui, strand.Storage storage) throws StrandException;

    /**
     * Indicates whether the command should continue running.
     *
     * @return {@code true} if the command should continue running; {@code false} otherwise.
     */
    public Boolean isRunning() {
        return true;
    }
}
