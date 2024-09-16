package bobby.command;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.ui.Ui;


/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command, which generates an exit message for the user.
     * This method does not modify the task list or storage, as it simply signals the application's termination.
     *
     * @param tasks   The current task list (not used in this command).
     * @param ui      The UI used to display the exit message.
     * @param storage The storage system (not used in this command).
     * @return A farewell message indicating the end of the program.
     * @throws BobbyException This command does not throw any exceptions,
     *     but the signature is required by the interface.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        return ui.getExitMessage();
    }
}
