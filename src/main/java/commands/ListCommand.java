package commands;

import storage.Storage;
import storage.TaskList;

/**
 * Represents a command that lists all tasks in the task list.
 * The ListCommand class displays the current tasks to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying all tasks currently in the task list.
     * If there are no tasks, a message is shown suggesting the user add tasks.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        if (master.getSize() <= 0) {
            return "Friday > No tasks in here! Try adding something!";
        }
        return "Friday > Here's everything!\n" + master;
    }
}
