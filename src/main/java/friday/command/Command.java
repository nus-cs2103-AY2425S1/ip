package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

/**
 * Represents an abstract command in the command pattern.
 * All concrete command classes will inherit from this class and implement specific command functionality.
 */
public abstract class Command {

    /**
     * Executes the command with the given TaskList, Ui, and Storage objects.
     * Concrete command classes will define the specific actions to be performed.
     *
     * @param tasks  The TaskList containing the tasks to be manipulated by the command.
     * @param ui     The Ui object for interacting with the user and providing feedback.
     * @param storage The Storage object for saving or loading tasks from storage.
     * @return A String containing the result of the command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether the command indicates the termination of the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean shouldExit();
}
