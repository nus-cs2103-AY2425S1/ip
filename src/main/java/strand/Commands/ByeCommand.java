package strand.Commands;

import strand.Storage;
import strand.TaskList;
import strand.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public Boolean isRunning() {
        return false;
    }
}
