package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

/**
 * Represents an abstract base class for commands in the Edith chatbot application.
 * Commands are actions that the chatbot can execute based on user input.
 * This class provides a common interface for all concrete command implementations.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, user interface, and storage.
     * Subclasses should implement this method to define the specific behavior of the command.
     *
     * @param tasks The list of tasks to be manipulated by the command.
     * @param ui The user interface for displaying messages and prompts.
     * @param storage The storage for saving and loading tasks.
     * @throws EdithException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException;

    /**
     * Determines whether this command signifies an exit action.
     * By default, commands do not signify an exit action.
     * Subclasses can override this method to return true if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
