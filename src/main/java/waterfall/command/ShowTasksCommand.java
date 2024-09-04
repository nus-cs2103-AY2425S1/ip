package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.TaskList;

/**
 * Represents the <code>Command</code> object to display all tasks
 * in the data storage to the user.
 *
 * @author Wai Hong
 */

public class ShowTasksCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskListMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
