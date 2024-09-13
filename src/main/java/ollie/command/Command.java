package ollie.command;

import ollie.History;
import ollie.Response;
import ollie.exception.OllieException;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;

/**
 * Represents a command. A command must be executable, and when executed,
 * certain side effects may be invoked.
 */
public abstract class Command {
    /**
     * Returns a response object to be processed by Main Window
     */
    public abstract Response execute(TaskList tasks, Ui ui, Storage storage, History history) throws OllieException;
}
