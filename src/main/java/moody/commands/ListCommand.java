package moody.commands;

import moody.storage.Storage;
import moody.tasks.TaskList;
import moody.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves all tasks and displays them to the user through the user interface.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks The task list to be displayed.
     * @param ui The user interface for showing the tasks.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
