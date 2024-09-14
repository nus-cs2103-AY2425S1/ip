package hana.command;

import hana.HanaException;
import hana.storage.Storage;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Represents a command with execute and isExit functions.
 * This is base class for different types of commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object to handle reading/writing of tasks.
     * @throws HanaException If an error occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws HanaException;

    /**
     * Indicates if command will exit the application.
     *
     * @return false by default.
    */
    public boolean isExit() {
        return false;
    }
}
