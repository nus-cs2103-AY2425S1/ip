package sage.Command;

import sage.List.TaskList;
import sage.Storage;
import sage.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command to display all tasks currently stored in the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object to handle user interaction.
     * @param storage The Storage object for loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.listTasks("Here are the tasks in your list:");
    }
}
