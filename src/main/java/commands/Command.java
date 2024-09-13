package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

/**
 * Represents a command that can be executed within the Friday.Friday application.
 * The Command interface defines a method that must be implemented by all concrete command classes.
 */
public interface Command {

    /**
     * Executes the command using the provided storage, task list, and user interface.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @param ui the UI object for interacting with the user
     * @return true if the command should terminate the application, false otherwise
     */
    boolean execute(Storage storage, TaskList master, UI ui);
}
