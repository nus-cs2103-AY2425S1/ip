package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * This class handles the display of all tasks currently in the task list
 * to the user interface.
 * </p>
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command to list all tasks.
     * <p>
     * This method updates the user interface to display all tasks in the task list.
     * </p>
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui       The user interface that handles the display of the tasks.
     * @param storage  The storage (not used in this command).
     * @return A string message that shows the list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.listItems(taskList);
    }
}
