package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
