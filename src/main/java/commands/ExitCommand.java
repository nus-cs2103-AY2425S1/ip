package commands;

import common.Ui;
import common.Command;
import storage.TaskStorage;

public class ExitCommand extends Command {
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        ui.showGoodbye();
        return false;
    }
}
