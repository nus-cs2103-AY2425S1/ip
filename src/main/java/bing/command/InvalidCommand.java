package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

public class InvalidCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
