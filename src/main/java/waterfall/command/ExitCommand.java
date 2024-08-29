package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to exit.
 *
 * @author Wai Hong
 */

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
