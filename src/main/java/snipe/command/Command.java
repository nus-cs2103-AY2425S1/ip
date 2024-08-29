package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code Command} class represents an abstract command that can be executed
 * to perform an action on the task list. It serves as a base class for all specific commands.
 * Each command must implement the {@link #execute(TaskList, Ui, Storage)} method to define its behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, user interface, and storage.
     * This method must be implemented by all subclasses to provide specific behavior for each command.
     *
     * @param tasks   The task list on which the command will operate.
     * @param ui      The user interface used to display messages to the user.
     * @param storage The storage object used to save or load the task list.
     * @throws SnipeException If an error specific to the application occurs during execution.
     * @throws IOException    If an input or output error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException;

    /**
     * Indicates whether this command is an exit command.
     * By default, this method returns {@code false}, meaning that the command does not exit the application.
     * Subclasses can override this method to indicate that they are exit commands.
     *
     * @return {@code true} if the command is an exit command, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;  // Default behavior is that the snipe.command is not an exit snipe.command
    }
}