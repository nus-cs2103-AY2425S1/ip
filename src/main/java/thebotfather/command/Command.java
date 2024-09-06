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
     * Executes this command, performing operations on the provided task list, interacting with the user through the
     * user interface, and saving or loading data using the storage system.
     *
     * @param taskList The list of tasks on which the command will operate.
     * @param ui The user interface that facilitates interaction with the user.
     * @param storage The storage system responsible for saving and loading task data.
     * @return A string message representing the result of the command execution, typically displayed to the user.
     * @throws TheBotFatherException If an error occurs while executing the command, particularly during data storage.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException;

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
}
