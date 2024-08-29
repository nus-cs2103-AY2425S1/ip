package darwin.command;

import darwin.exception.DarwinException;
import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * Command class to represent a command to be executed by the user.
 */
public abstract class Command {
    private String args;

    /**
     * Executes the command.
     * @param taskManager task manager to execute the command
     * @param ui user interface to display messages
     * @throws DarwinException if an error occurs during execution
     */
    public abstract void execute(TaskManager taskManager, Ui ui) throws DarwinException;

    /**
     * Returns true if the command is an exit command.
     * @return true if the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
