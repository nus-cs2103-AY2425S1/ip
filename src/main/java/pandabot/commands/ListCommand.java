package pandabot.commands;

import pandabot.storage.Storage;
import pandabot.storage.TaskList;
import pandabot.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command displays the current tasks in the list to the user.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return "There are currently no items in the list.";
        } else {
            return "Here are the tasks in your list:\n"
                    + tasks.printTasks();
        }
    }
}
