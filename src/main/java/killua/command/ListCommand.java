package killua.command;

import killua.storage.Storage;
import killua.ui.Ui;
import killua.util.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves the current list of tasks and displays them to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks.
     * Retrieves the list of tasks from the task list and updates the user interface to show these tasks.
     *
     * @param tasks The task list containing all the tasks.
     * @param ui The user interface to interact with and display the task list.
     * @param storage The storage to save the task list (not used in this command).
     * @return A String representation of Killua's response.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
