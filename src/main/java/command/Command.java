package command;

import main.Storage;
import main.TaskList;

/**
 * Abstract class representing a command in the application.
 * Subclasses should implement the specific action of the command.
 */
public abstract class Command {

    /**
     * Checks if this command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command with the given task list and storage.
     *
     * @param tasks   The task list to operate on.
     * @param storage The storage to interact with.
     */
    public abstract String execute(TaskList tasks, Storage storage);
}