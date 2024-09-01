package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * Concrete subclasses should implement the {@link #execute(TaskList, Ui, Storage)} method to define
 * the specific behavior of the command.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks The {@code TaskList} containing the tasks.
     * @param ui The {@code Ui} object for interacting with the user.
     * @param storage The {@code Storage} object for loading and saving tasks.
     * @throws MorganaException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException;

    /**
     * Determines whether the command should cause the application to exit.
     * By default, this method returns {@code false}. Subclasses may override
     * this method if they represent a command that should exit the application.
     *
     * @return {@code true} if the command should exit the application, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
