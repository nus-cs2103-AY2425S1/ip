package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * Inherits from the Command class and provides functionality to display all tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
