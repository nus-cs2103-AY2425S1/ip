package dudu.command;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a command to list current tasks.
 */
public class ListCommand extends Command {
    /**
     * Returns the current list of tasks
     *
     * @param taskList Task list containing the tasks.
     * @param ui User interface to interact with the user.
     * @param storage Storage to save tasks.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.getListTasksMessage(taskList);
    }

    /**
     * Compares this object with another object.
     *
     * @param object Object to compare with.
     * @return True if object is a ListCommand object else false.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return object instanceof ListCommand;
    }
}
