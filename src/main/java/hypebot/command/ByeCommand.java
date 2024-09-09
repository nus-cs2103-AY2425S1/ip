package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        try {
            ui.showSavingTasks();
            storageManager.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showExit();
        }
    }
}
