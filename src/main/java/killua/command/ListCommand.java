package killua.command;

import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}