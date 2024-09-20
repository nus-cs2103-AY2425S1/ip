package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.TaskList;

/**
 * Represents a command that can be executed to perform an action on the task list.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list, user interface, and storage.
     *
     * @param taskList the task list on which the command operates
     * @param ui       the user interface for displaying messages and interacting with the user
     * @param storage  the storage for saving and loading tasks
     * @return a message indicating the result of the command execution
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
