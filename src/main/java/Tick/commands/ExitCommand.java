package Tick.commands;

import Tick.storage.Storage;
import Tick.tasks.TaskList;
import Tick.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
