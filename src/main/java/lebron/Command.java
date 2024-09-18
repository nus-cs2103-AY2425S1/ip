package lebron;

/**
 * Represents an abstract command that can be executed. Subclasses must
 * implement the {@code execute} method to define the specific actions of the
 * command.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, user interface, and
     * storage. Subclasses should implement this method to define the behavior
     * of the command.
     *
     * @param taskList The task list to be amended by the command.
     * @param ui The user interface that will display messages.
     * @param storage The storage where tasks are saved or retrieved.
     * @throws LeBronException If an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException;

    /**
     * Indicates whether the command is an exit command. This method can be
     * overridden by subclasses if the command should cause the application to
     * exit.
     *
     * @return {@code true} if the command is an exit command, {@code false}
     *     otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
