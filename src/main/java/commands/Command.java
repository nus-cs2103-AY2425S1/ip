package commands;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents a command that can be executed by the application.
 * Implementations of this interface define specific actions that can be performed on the task list.
 */
public interface Command {
    /**
     * Executes the command.
     * This method performs the action associated with the command on the provided task list, using the UI for interaction
     * and the storage for saving/loading tasks.
     *
     * @param tasks the task list on which the command is to be executed.
     * @param ui the UI used to interact with the user.
     * @param storage the storage used to save or load tasks.
     * @throws IOException if an I/O error occurs during command execution.
     * @throws InputException if there is an error in the user input.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InputException;

    /**
     * Determines if the command is an exit command.
     * The application will terminate if this method returns true.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}
