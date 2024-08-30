package sage.command;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.ui.Ui;
import sage.storage.Storage;

public abstract class Command {
    /**
     * Executes the user command
     *
     * @param tasks The task list the command operates on
     * @param ui The Ui that interacts with the user
     * @param storage The storage to save/load tasks
     * @throws SageException If the command execution fails
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SageException;

    /**
     * Returns whether the command should exit the application
     *
     * @return true if the command should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
