package atreides.command;

import atreides.task.TaskList;
import atreides.ui.AtreidesException;
import atreides.ui.Storage;
import atreides.ui.Ui;

/**
 * Represents a command to find and display a list of tasks containing a specific keyword.
 *
 * This class implements the Command interface, providing functionality to search through
 * a TaskList for tasks that match the given description, and to display the search results.
 */
public class FindCommand implements Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command to search for tasks with descriptions that contain the specified keyword.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for handling any changes to the task list.
     * @return A formatted string listing all tasks that match the given keyword.
     * @throws AtreidesException If an error occurs during the execution of the command.
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        assert !description.isEmpty();
        return tasks.find(description);
    }

    /**
     * Executes the command and displays the result to the user.
     *
     * @param tasks The TaskList containing all tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for handling any changes to the task list.
     * @throws AtreidesException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException {
        String response = executeString(tasks, ui, storage);
        ui.showMessage(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
