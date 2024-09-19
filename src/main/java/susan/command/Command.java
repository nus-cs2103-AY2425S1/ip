package susan.command;

import susan.ui.Storage;
import susan.ui.SusanException;
import susan.task.TaskList;
import susan.ui.Ui;

/**
 * Abstract class representing the commands to be called by user.
 */
public abstract class Command {
    /**
     * Returns string to be printed to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws SusanException;
}