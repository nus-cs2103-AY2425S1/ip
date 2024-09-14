package killua.command;

import java.io.IOException;

import killua.storage.Storage;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents an abstract command in the Killua application.
 * Commands encapsulate actions to be performed on tasks, such as adding, deleting, or listing tasks.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The user interface to interact with.
     * @param storage The storage to read from or write to.
     * @return A String representation of Killua's response.
     * @throws KilluaException If there is an error in the execution related to task handling.
     * @throws IOException If there is an error in reading or writing to storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException;

    /**
     * Checks if this command signals the application to exit.
     *
     * @return {@code true} if the command indicates the application should exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
