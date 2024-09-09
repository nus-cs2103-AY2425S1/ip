package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

/**
 *
 */
public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    public abstract void execute(Tasklist tasks, Ui ui, StorageManager storageManager);
}
