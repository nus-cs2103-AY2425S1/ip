package star.command;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;

/**
 * Represents a Command that enables the execution of a set of actions.
 */
public class Command {

    /**
     * Executes the set of actions within.
     * @param tasks   which is the task list on which the command is executed.
     * @param ui      which is the UI that is used to interact with the user.
     * @param storage which is the storage used to load and save tasks.
     * @throws StarException which is an Exception specific to Star that is thrown when there is an error.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        return "";
    }

    /**
     * Determines if the Command is an ExitCommand or not.
     * The application will exit if this method returns True.
     * @return True if the Command is an ExitCommand, and False otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
