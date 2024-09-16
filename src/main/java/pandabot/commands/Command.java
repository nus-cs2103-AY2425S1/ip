package pandabot.commands;

import java.io.IOException;

import pandabot.exceptions.InputException;
import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a command that can be executed by the application.
 * Implementations of this interface define specific actions that can be performed on the task list.
 */
public abstract class Command {
    /**
     * Executes the command.
     * This method performs the action associated with the command on the provided task list,
     * using the UI for interaction and the storage for saving/loading tasks.
     *
     * @param tasks the task list on which the command is to be executed.
     * @param ui the UI used to interact with the user.
     * @param storage the storage used to save or load tasks.
     * @return A string representing the result or output message of the executed command.
     * @throws IOException if an I/O error occurs during command execution.
     * @throws InputException if there is an error in the user input.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InputException;
}
