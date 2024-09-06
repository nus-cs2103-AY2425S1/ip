package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.TaskList;

/**
 * The <code> ListCommand </code> class represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by displaying the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     * @param ui       The UI object used to display the task list.
     * @param storage  The storage object (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList);
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return false because the ListCommand does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
