package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface for interacting with the user.
     * @param storage The storage for saving the task list. Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
