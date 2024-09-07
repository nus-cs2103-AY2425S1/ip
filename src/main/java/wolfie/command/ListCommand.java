package wolfie.command;

import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Excutes the list command to list all tasks in the task list.
     * @param tasks   The list of tasks
     * @param ui      The user interface
     * @param storage The storage object
     * @return A message with all the tasks in the task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks);
    }
}
