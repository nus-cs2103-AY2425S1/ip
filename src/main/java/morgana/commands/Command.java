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
     * Returns the style class for this command, which can be applied to the
     * {@code dialog} in {@link DialogBox}.
     *
     * @return The style class for this command, or {@code null} if none is specified.
     */
    public String getStyleClass() {
        return null;
    }
}
