package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command that advises the user to use the "bye" command to exit the application.
 * The ExitCommand class is a catch-all command meant to guide users who might attempt to use other
 * commands like "exit" to leave the application, directing them to the correct command.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command, advising the user to use the "bye" command to exit the application.
     * This method prints a message to the user and returns false, indicating that the application should not terminate.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return false, indicating that the application should not terminate
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        return "Friday > Type \"bye\" or \"Bye\" to exit";
    }
}
