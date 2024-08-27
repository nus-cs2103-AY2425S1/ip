package Tick.commands;

import Tick.storage.Storage;
import Tick.tasks.TaskList;
import Tick.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
