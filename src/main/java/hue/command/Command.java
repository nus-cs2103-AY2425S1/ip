package hue.command;

import java.io.IOException;
import hue.UI.UI;
import hue.task.*;
import hue.HueException;
import hue.storage.Storage;


/**
 * Represents a command that can be executed.
 * <p>
 * This is an abstract class that defines the structure for all commands.
 * Concrete subclasses should implement the {@link #execute(TaskList, UI, Storage)} method to define specific command behavior.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to interact with.
     * @param storage The storage to save tasks.
     * @throws HueException If there is an error executing the command.
     * @throws IOException  If there is an error saving tasks.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws HueException, IOException;

    /**
     * Indicates if this command is an exit command.
     *
     * @return True if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}


