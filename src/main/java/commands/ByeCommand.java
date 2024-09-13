package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command to exit the Friday.Friday application.
 * The ByeCommand class implements the Command interface and handles terminating the application.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command, signaling the application to terminate.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @param ui the UI object for interacting with the user
     * @return true, indicating that the application should terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        return true;
    }
}
