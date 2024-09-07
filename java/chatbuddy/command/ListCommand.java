package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying the tasks in the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface to display the tasks.
     * @param storage The storage (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return ui.getOutput();
    }
}
