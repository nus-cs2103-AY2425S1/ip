package susan.command;

import susan.ui.Storage;
import susan.task.TaskList;
import susan.ui.Ui;

/**
 * Represents a command to display the current task list to the user.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}