package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.TaskList;

/**
 * Represents the <code>Command</code> object to exit.
 *
 * @author Wai Hong
 */

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
