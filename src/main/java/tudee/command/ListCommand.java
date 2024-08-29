package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;

/**
 * Represents a command to display the list of tasks.
 * This command retrieves the current task list and updates the
 * user interface to show the tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to display the list of tasks.
     * Updates the user interface to show the current tasks.
     *
     * @param tasks The task list to be displayed.
     * @param ui The user interface to update with the task list.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.get());
    }
}