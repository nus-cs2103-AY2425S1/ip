package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

/**
 * Represents an abstract Command that can be executed.
 * A Command typically interacts with a {@code TaskList}, {@code UI}, and {@code Storage}.
 * Subclasses should provide an implementation for the {@code execute} method.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Constructs a Command object.
     *
     * @param isExit Specifies whether the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object that handles saving and loading tasks.
     * @return        The result of the command execution as a string.
     * @throws HienException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws HienException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
