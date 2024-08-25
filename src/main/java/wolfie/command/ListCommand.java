package wolfie.command;

import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
