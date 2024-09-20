package Alex.Command;

import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to list all tasks in the TaskList using the Ui.
 */
public class ListCommand extends CommandBase {

    /**
     * Executes the ListCommand by listing all specified tasks and
     * updating the Ui with a success message
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance responsible for displaying output to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getAllTasks());
    }
}
