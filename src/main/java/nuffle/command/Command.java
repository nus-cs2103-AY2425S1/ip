package nuffle.command;

import nuffle.exception.NuffleException;
import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.ui.Ui;


/**
 * The Command class is an abstract class that represents a command executed in the Nuffle application.
 * Subclasses of Command will implement specific actions that the user can perform on the task list.
 * The command interacts with the task list, storage, and user interface to perform its operation.
 */

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The TaskList the command is operating on.
     * @param storage The Storage component used to save/load tasks.
     * @param ui The UI component used to interact with the user.
     * @return The result of executing the command as a string.
     * @throws NuffleException If any issue arises during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws NuffleException;

    public abstract boolean isTerminateProgram();

}
