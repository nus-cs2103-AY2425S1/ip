package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to alert user when
 * the command is not recognised.
 *
 * @author Wai Hong
 */

public class UnrecognisedCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Bro whatchu yapping! I don't know what are you talking about");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
