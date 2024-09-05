package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.TaskList;
import morgana.ui.DialogBox;

/**
 * Represents an abstract command that can be executed by the application.
 * Concrete subclasses should implement the {@link #execute(TaskList, Storage)} method to define
 * the specific behavior of the command.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and storage.
     *
     * @param tasks The {@code TaskList} containing the tasks.
     * @param storage The {@code Storage} object for loading and saving tasks.
     * @return The feedback message to be displayed after the command is executed.
     * @throws MorganaException If an error occurs while executing the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws MorganaException;

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

    /**
     * Returns the style class for this command, which can be applied to the {@code dialog} in {@link DialogBox}.
     *
     * @return The style class for this command, or {@code null} if none is specified.
     */
    public String getStyleClass() {
        return null;
    }
}
