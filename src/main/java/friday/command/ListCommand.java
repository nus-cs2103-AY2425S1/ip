package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * Inherits from the Command class and provides functionality to display all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}