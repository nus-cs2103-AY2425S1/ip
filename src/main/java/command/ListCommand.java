package command;
import components.Storage;
import components.Ui;
import task.TaskList;

/**
 * Represents a command to list the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Returns nothing. Executes listing the tasks in the TaskList.
     * @param taskList TaskList object to add the task to.
     * @param ui Ui object for interacting with user.
     * @param storage Storage object for loading and saving data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.showMessage(taskList.displayTasks());
    }

}
