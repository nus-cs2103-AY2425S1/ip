package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents an abstract command that can be executed.
 * All specific command types should extend this class and implement its abstract methods.
 */
public abstract class Command {

    /**
     * Executes the command with the specified task list, UI, and storage.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The Storage object to save and load tasks.
     * @throws DrBrownException If there is an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException;

    /**
     * Determines whether the command exits the program.
     *
     * @return true if the command exits the program, false otherwise.
     */
    public abstract boolean isExit();
}
