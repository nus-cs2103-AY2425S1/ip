package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by displaying an exit message and saving the current task list to storage.
     *
     * @param tasks the task list to be saved before exiting
     * @param storage the storage object to save the task list
     * @throws BarcusException if there is an error during storage upload
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        output = "Alright, good talk!";
        storage.upload(tasks);
    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return true, as this command is intended to terminate the application
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
