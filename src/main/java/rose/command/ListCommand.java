package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

/**
 * Represents a command used by user to show the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display the current tasks.
     *
     * @param tasks The TaskList containing the tasks to be shown.
     * @param ui The Ui instance for displaying messages to the user.
     * @param storage The Storage instance, not used in this method.
     * @return A message displaying the list of tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.showTasks(ui);
    }
}
