package NextGPT.command;

import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;

/**
 * Abstract command class that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command based on its type, e.g. AddCommand, EditCommand, ExitCommand etc.
     */
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws NextGPTException;

    /**
     * Checks whether this is an ExitCommand.
     * @return True if this is an ExitCommand.
     */
    public abstract boolean isExit();
}
