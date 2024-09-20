package vinegar.command;

import vinegar.task.TaskList;
import vinegar.storage.Storage;
import vinegar.ui.Ui;

/**
 * Lists all tasks in the task list.
 * <p>
 * The ListCommand class is responsible for displaying the current tasks
 * in the task list using the UI.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by showing all tasks in the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for displaying messages to the user.
     * @param storage The Storage for saving the updated task list (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
