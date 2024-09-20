package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents an abstract command in the King application.
 * All specific commands should extend this class and implement the {@code execute} method.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks The task list to perform operations on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save or load tasks.
     * @throws KingException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws KingException;

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public abstract boolean isExit();
}
