package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents an abstract command in the application.
 * <p>
 * Concrete implementations of this class define specific commands that can be executed.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The list of tasks to be manipulated by the command.
     * @param ui The user interface to interact with the user.
     * @param storage The storage used for reading from and writing to the file.
     * @throws TheBotFatherException If there is an error during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException;

    /**
     * Returns whether the command signifies an exit command.
     * <p>
     * The default implementation returns {@code false}, indicating that this command does not exit the application.
     * </p>
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

    ;
}
