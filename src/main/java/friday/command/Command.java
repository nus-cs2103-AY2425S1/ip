package friday.command;

import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

/**
 * Represents an abstract command in the application.
 * Subclasses must implement the execute method to define specific command behavior.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to be used by the command.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list.
     * @throws IOException       If an input/output error occurs during execution.
     * @throws FridayException   If there is an error specific to the command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException;

    /**
     * Determines if this command is an exit command.
     *
     * @return false by default, indicating that the command does not signify an exit.
     */
    public boolean isExit() {
        return false;
    }
}


