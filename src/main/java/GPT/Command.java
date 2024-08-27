package GPT;

/**
 * Represents an abstract command that can be executed in the GPT application.
 * Subclasses of Command should implement the execute method to define the behavior
 * of the specific command.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface, and storage.
     * Subclasses must implement this method to provide the specific behavior for the command.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface that displays messages to the user.
     * @param storage  The storage handler that manages task persistence.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    /**
     * Indicates whether this command will exit the program.
     * By default, this returns false, meaning the program will continue running after
     * the command is executed. Subclasses can override this method to return true if
     * the command should terminate the application (e.g., a "bye" command).
     *
     * @return false by default, indicating that the program should not exit.
     */
    public boolean isExit() {
        return false;
    }
}