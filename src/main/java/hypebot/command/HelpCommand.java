package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class HelpCommand extends Command{
    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        ui.showHelpMessage();
    }
}
