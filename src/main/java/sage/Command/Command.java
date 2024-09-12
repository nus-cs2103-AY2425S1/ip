package sage.Command;

import sage.List.TaskList;
import sage.SageException;
import sage.Storage;
import sage.Ui;

/**
 * Represents an abstract command in the application.
 */
public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for handling user interaction.
     * @param storage The Storage object for saving changes to the file.
     * @throws SageException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws SageException;
}
