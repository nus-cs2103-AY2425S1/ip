package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

/**
 * Represents a command to sort all tasks in the task list.
 * Inherits from the Command class and provides functionality to display all tasks.
 */
public class SortCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        TaskList sortedTasks = tasks.sortTasksByDate();
        return ui.showSortedTasks(sortedTasks);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

}
