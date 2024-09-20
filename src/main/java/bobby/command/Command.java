package bobby.command;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.ui.Ui;

/**
 * The Command interface defines a contract for all command operations
 * in the application. Commands interact with the task list, user interface, and storage.
 * The interface ensures that each command can be executed consistently, while leaving
 * the specific execution details to the individual command classes.
 */
public interface Command {

    /**
     * Executes the command with the provided task list, user interface, and storage.
     * Each command is responsible for its specific behavior, which may include
     * modifying the task list, interacting with the user, and updating storage.
     *
     * @param tasks   the {@code TaskList} containing the tasks to be operated on
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return A String message reflecting the result of the command execution.
     * @throws BobbyException if an error occurs during command execution
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException;
}
