package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to exit the DrBrown application.
 * This command handles the process of saving the current state of the task list
 * and provides a message indicating that the application is closing.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the current state of the task list to storage
     * and displaying an exit message to the user. It ensures that all tasks are properly
     * updated in the storage before the application exits.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The UI object to display messages to the user and handle UI closure.
     * @param storage The Storage object used to update the saved tasks.
     * @return A string message indicating that the application is exiting.
     * @throws DrBrownException If there is an error updating the storage.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        storage.update(tasks);
        return ui.showEnd();
    }

    /**
     * Indicates whether this command exits the program.
     * This method is always true for ExitCommand as its purpose is to terminate the application.
     *
     * @return true, as this command exits the program.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
