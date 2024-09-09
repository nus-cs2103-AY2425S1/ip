package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showListingTasks(tasks);
    }
}
