package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;

/**
 * The {@code Command} class represents an abstract command that can be executed
 * to perform an action on the task list. It serves as a base class for all specific commands.
 * Each command must implement the {@link #getResponse(TaskList, Ui, Storage, TaskList, Storage)} method to define its behavior.
 */
public abstract class Command {

    /**
     * Executes the command and returns the response as a string.
     * This method must be implemented by all subclasses to define the specific behavior of each command
     * based on the given task list, user interface, and storage.
     *
     * @param tasks          The {@link TaskList} that the command will manipulate or query.
     * @param ui             The {@link Ui} instance used to interact with the user, display messages, and gather input.
     * @param storage        The {@link Storage} instance responsible for saving and loading the task list data.
     * @param archiveTasks   The {@link TaskList} that the command will manipulate or query.
     * @param archiveStorage The {@link Storage} instance responsible for saving
     *                       and loading the archived task list data.
     * @return The response message as a string, typically used to provide feedback to the user.
     * @throws SnipeException If an application-specific error occurs during command execution.
     * @throws IOException    If an I/O error occurs while accessing or modifying storage.
     */
    public abstract String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException, IOException;


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