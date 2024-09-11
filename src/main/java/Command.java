/**
 * Represents an abstract command that can be executed in the application.
 * Each subclass of Command will implement the specific behavior for its command by overriding the abstract methods.
 * Commands interact with a task list and the user interface during their execution.
 *
 * @author Aaron
 */
public abstract class Command {
    /**
     * Executes the command and performs the necessary operations on the task list and user interface.
     *
     * @param tasks The task list that the command will operate on.
     * @param ui The Ui instance that handles interaction with the user.
     * @throws ElsaException If an error occurs during the execution of the command
     */
    public abstract void execute(TaskList tasks, Ui ui) throws ElsaException;

    /**
     * Determines whether this command is a goodbye (exit) command.
     * This method can be overridden by subclasses like ByeCommand to indicate that
     * the command is meant to terminate the application.
     *
     * @return true if the command signals the application to exit, false otherwise.
     *         By default, this returns false and is expected to be overridden by exit-related commands.
     */
    public boolean isGoodbye() {
        return false;
    }
}
