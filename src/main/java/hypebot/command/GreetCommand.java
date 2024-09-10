package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class GreetCommand extends Command {
    public GreetCommand() {
        super();
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showGreeting();
    }
}
