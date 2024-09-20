package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to display all tasks in the user's task list.
 * A <code>ListCommand</code> object outputs the current list of tasks
 * or a message if the list is empty.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskCount() == 0) {
            return ui.showList("You currently have no tasks");
        }
        String tasksAsString = tasks.toString();
        return ui.showList(tasksAsString);
    }
}
