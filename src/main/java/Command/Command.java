package Command;

import Tools.Storage;
import Tools.TaskList;

/**
 * Represents a generic command within the application that acts on a task list.
 * This class serves as a base class for more specific command implementations that execute various actions.
 */
public class Command {
    protected TaskList tasks;   // Holds the reference to the task list to be manipulated.
    protected Storage storage;  // Responsible for handling persistence of task data.
    protected String command;   // Stores the actual command string provided by the user.

    /**
     * Constructs a new Command with specified task list, storage, and command text.
     *
     * @param tasks    The TaskList to operate on.
     * @param storage  The Storage utility to handle task persistence.
     * @param command  The specific command text that describes the action to be performed.
     */
    public Command(TaskList tasks, Storage storage, String command) {
        this.tasks = tasks;
        this.storage = storage;
        this.command = command;
    }
}

