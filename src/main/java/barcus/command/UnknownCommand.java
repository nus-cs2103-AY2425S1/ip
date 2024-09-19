package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to handle unknown or invalid input.
 */
public class UnknownCommand extends Command {
    /**
     * Executes the command by throwing an exception to indicate that the command is not recognized.
     *
     * @param tasks the task list (not used in this command)
     * @param storage the storage object (not used in this command)
     * @throws BarcusException with a message indicating that the command is not found
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        throw new BarcusException("command not found D:");
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as handling unknown commands does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
