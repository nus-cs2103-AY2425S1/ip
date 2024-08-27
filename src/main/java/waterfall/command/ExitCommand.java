package waterfall.command;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.*;

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
