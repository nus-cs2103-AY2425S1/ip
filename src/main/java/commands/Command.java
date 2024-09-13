package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;


/**
 * Represents a command that can be executed.
 * Implementations of this interface define the behavior of various commands
 * in the application. Each command interacts with the task list, user interface,
 * and storage to perform its function.
 */
public interface Command {

    /**
     * Executes the command with the provided task list, user interface, and storage.
     *
     * @param tasks   The task list that the command will operate on.
     * @param ui      The user interface to interact with.
     * @param storage The storage to read from or write to.
     * @return A string indicating the result or output of the command.
     */
    String execute(TaskList tasks, Ui ui, Storage storage);
}
