package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanException;
import stan.ui.Ui;


/**
 * Represents an abstract command that can be executed.
 * All specific command types should extend this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list where tasks are managed.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save or load tasks.
     * @throws StanException If an error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws StanException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
