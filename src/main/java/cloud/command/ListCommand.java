package cloud.command;

import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Prints the user's list of tasks.
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
