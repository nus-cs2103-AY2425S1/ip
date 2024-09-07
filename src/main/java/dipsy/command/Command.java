package dipsy.command;

import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents an abstract command that can be executed in the application.
 * Specific command types should extend this class and implement the {@link #execute()} method.
 */
public class Command {
    /** The user input that triggered this command. */
    protected final String userInput;

    /** The list of tasks stored in memory that the command can interact with. */
    protected final TaskList tasks;

    /** The UI handler for interacting with the user (e.g. printing statements). */
    protected final Ui ui;

    /**
     * Constructs a Command with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public Command(String userInput, TaskList tasks, Ui ui) {
        this.userInput = userInput;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the command.
     * Subclasses should override this method to provide specific command behavior.
     *
     * @throws InvalidCommandException If the command is invalid.
     * @throws InvalidDateException If the command contains an invalid date.
     */
    public void execute() throws InvalidCommandException, InvalidDateException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    /**
     * Saves the current state of tasks in memory to the local disk, allowing for retrieval upon program restart.
     * This method delegates the saving process to the {@link TaskList#saveToLocalDisk()} method.
     */
    protected void saveTasksToLocalDisk() {
        tasks.saveToLocalDisk();
    }
}
