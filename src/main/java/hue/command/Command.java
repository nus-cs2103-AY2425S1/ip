package hue.command;

import java.io.IOException;
import hue.ui.ui;
import hue.task.*;
import hue.HueException;
import hue.storage.Storage;


/**
 * Represents a command that can be executed.
 * <p>
 * This is an abstract class that defines the structure for all commands.
 * Concrete subclasses should implement the {@link #execute(TaskList, ui, Storage)} method to define specific command behavior.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the command based on the provided task list and other relevant parameters.
     *
     * This abstract method should be implemented by concrete subclasses of {@code Command}.
     * Each subclass will define its own specific behavior for executing the command.
     * The method typically interacts with the task list, user interface, and storage
     * to perform the command's operation and return a result string to provide feedback.
     *
     * @param tasks The {@code TaskList} containing all the tasks. Used to retrieve or modify tasks as needed by the command.
     * @param ui The {@code UI} instance used for interacting with the user, such as showing messages or errors.
     * @param storage The {@code Storage} instance used for saving any changes to the task list.
     * @return A string message indicating the result of the command execution.
     * @throws IOException If there is an error during interactions with the storage, such as saving tasks.
     * @throws HueException If there is an error related to the command execution, such as invalid parameters or task issues.
     */
    public abstract String execute(TaskList tasks, ui ui, Storage storage) throws HueException, IOException;

    /**
     * Indicates if this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}


