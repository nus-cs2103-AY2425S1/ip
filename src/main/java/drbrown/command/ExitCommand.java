package drbrown.command;

import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

/**
 * Represents a command to exit the DrBrown application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing the end message, closing the command UI,
     * and updating the task list in storage.
     *
     * @param tasks   The TaskList containing the current tasks.
     * @param ui      The UI object to display messages to the user and handle UI closure.
     * @param storage The Storage object to update the saved tasks.
     * @throws DrBrownException If there is an error updating the storage.
     */

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        ui.showEnd();
        ui.closeCommand();
        storage.update(tasks);
    }

    /**
     * Indicates whether this command exits the program.
     *
     * @return true, as this command exits the program.
     */

    @Override
    public boolean shouldExit() {
        return true;
    }
}
