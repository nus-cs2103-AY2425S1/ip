package ollie.command;

import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command. A command must be executable, and when executed,
 * certain side effects may be invoked.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Return whether this command represents the end of the conversation.
     *
     * @return boolean True if this commands ends the conversation, false if otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException;
}
