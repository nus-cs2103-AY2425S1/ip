package commands;

import storage.Storage;
import storage.TaskList;

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
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        return "Friday > Bye! See you soon!";
    }
}
