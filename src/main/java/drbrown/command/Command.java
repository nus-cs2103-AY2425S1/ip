package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents an abstract command that can be executed in the DrBrown application.
 * All specific command types should extend this class and implement its abstract methods
 * to provide custom functionality.
 */
public abstract class Command {

    /**
     * Executes the command using the provided task list, user interface, and storage.
     * Implementing classes must provide the logic for their specific command execution,
     * including any interactions with the user through the UI and updating tasks in the storage.
     *
     * @param tasks   The TaskList containing the current tasks to operate on.
     * @param ui      The UI object to display messages to the user and handle user input.
     * @param storage The Storage object used to save and load tasks from a file or persistent storage.
     * @return A string message representing the result of the command execution.
     * @throws DrBrownException If there is an error executing the command, such as invalid input or
     *     an operation failure.
     */
    public abstract String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException;

    /**
     * Determines whether the command causes the application to exit.
     * Implementing classes should override this method to indicate if the command will terminate
     * the application after execution.
     *
     * @return true if the command exits the program, false otherwise.
     */
    public abstract boolean shouldExit();
}
