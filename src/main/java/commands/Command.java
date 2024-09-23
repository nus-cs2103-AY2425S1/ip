package commands;

import storage.Storage;
import storage.TaskList;

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
     * @return String that results from a command execution that is to be displayed to the user
     */
    String execute(Storage storage, TaskList master);
}
