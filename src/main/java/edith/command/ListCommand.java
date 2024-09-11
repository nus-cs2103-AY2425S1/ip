package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * The ListCommand class is used to retrieve and display all tasks that are currently in the user's task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by listing all tasks in the task list.
     *
     * <p>This method will:
     * <ul>
     *     <li>Retrieve all tasks from the TaskList.</li>
     *     <li>Display the tasks to the user through the Ui.</li>
     * </ul>
     *
     * @param tasks The TaskList containing all tasks to be listed.
     * @param ui The Ui used to display the list of tasks to the user.
     * @param storage The Storage used to save changes (not used in this command).
     * @throws EdithException This method does not throw any EdithException.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        return tasks.listTasks(ui);
    }
}
