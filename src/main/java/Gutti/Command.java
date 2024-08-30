package Gutti;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException;

    /**
     * Returns boolean on whether this command should terminate the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}