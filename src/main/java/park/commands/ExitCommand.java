package main.java.park.commands;

import main.java.park.storage.Storage;
import main.java.park.storage.TaskList;
import main.java.park.ui.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
