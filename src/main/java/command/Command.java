package command;

import java.io.IOException;

import exception.ScheduloException;
import task.TaskList;
import util.Storage;


/**
 * The Command class is an abstract base class for all commands in the Schedulo application.
 * Each command represents a user action that can be executed, affecting the task list, UI, and storage.
 */
public abstract class Command {

    /**
     * Executes the command, performing the specific action associated with the command.
     *
     * @param tasks   The TaskList that the command will act upon.
     * @param storage The Storage instance used to save or load data.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException;

    /**
     * Indicates whether the command is an exit command, which terminates the application.
     *
     * @return True if the command is an exit command, false otherwise. Default is false.
     */
    public boolean isExit() {
        return false;
    }
}
