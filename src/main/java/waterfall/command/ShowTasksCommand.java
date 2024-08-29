package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to display all tasks
 * in the data storage to the user.
 *
 * @author Wai Hong
 */

public class ShowTasksCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskListMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
