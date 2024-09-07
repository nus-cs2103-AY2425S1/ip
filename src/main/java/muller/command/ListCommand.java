package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
