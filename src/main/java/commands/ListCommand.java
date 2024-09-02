package commands;

import storage.Storage;
import storage.TaskList;
import ui.UI;

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
     * @param ui the UI object for interacting with the user
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        if (master.getSize() <= 0) {
            System.out.println("Friday > No tasks in here! Try adding something!");
        } else {
            System.out.println("Friday > Here's everything!\n" + master);
        }
        UI.printLine();
        return false;
    }
}
