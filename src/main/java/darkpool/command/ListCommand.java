package darkpool.command;

import darkpool.gui.Gui;
import darkpool.util.Storage;
import darkpool.util.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param taskList The task list containing the tasks to be listed.
     * @param gui       The UI to display the list of tasks.
     * @param storage  The storage (not used in this command).
     * @return
     */
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) {
        return gui.list(taskList);
    }

}
