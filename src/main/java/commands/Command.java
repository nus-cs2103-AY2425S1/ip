package commands;

import exception.PrimoException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an abstract command in the application.
 * This class serves as a base for all command types and defines the contract
 * for executing commands and determining if the command results in an exit.
 */
public abstract class Command {

    /**
     * Checks whether this command is an exit command.
     * Subclasses should implement this method to indicate whether
     * the command signifies that the application should terminate.
     *
     * @return true if this command is an exit command; false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the command with the given task list, user interface, and storage.
     * Subclasses should implement this method to define the specific actions
     * that the command performs. The method can interact with the task list,
     * user interface, and storage as needed.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify or interact with the task list.
     * @param ui The user interface component. This parameter allows the command
     *           to interact with the user interface.
     * @param storage The storage component. This parameter allows the command
     *                to save or retrieve data from storage.
     * @throws PrimoException If an error occurs during command execution, such as
     *                        issues with task manipulation, UI interaction, or storage access.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PrimoException;
}
