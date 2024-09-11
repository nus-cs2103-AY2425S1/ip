package neuro.command;

import neuro.Storage;
import neuro.Ui;
import neuro.task.TaskList;

/**
 * The {@code Command} class represents an abstract command that can be executed within the Neuro application.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list, user interface and storage.
     *
     * @param tasks the task list on which the command operates
     * @param ui the user interface for interacting with the user
     * @param storage the storage for saving and loading tasks
     * @return a String message to be displayed by Neuro
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code true} if the command should cause the application to exit, {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}
