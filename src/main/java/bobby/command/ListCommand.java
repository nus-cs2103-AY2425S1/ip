package bobby.command;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * The {@code ListCommand} class encapsulates the logic for retrieving and displaying
 * all tasks within the task list. It provides a way to view the complete list of tasks
 * and interact with the user interface to present this information.
 * </p>
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by retrieving all tasks from the task list and
     * returning a message to display them.
     * <p>
     * The method uses the {@code Ui} instance to generate a message that includes
     * the complete list of tasks. The task list is not modified, but the list is
     * presented to the user through the UI.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be listed
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message with the complete list of tasks
     * @throws BobbyException This command does not throw any exceptions,
     *     but the signature is required by the interface.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        return ui.getTasksList(tasks);
    }
}
