package nextgpt.command;

import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.Storage;
import nextgpt.NextGPTException;

/**
 * Abstract command class that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command based on its type, e.g. AddCommand, EditCommand, ExitCommand etc.
     */
    public abstract String execute (TaskList tasks, Ui ui, Storage storage) throws NextGPTException;

    /**
     * Checks whether this is an ExitCommand.
     * @return True if this is an ExitCommand.
     */
    public abstract boolean isExit();
}
