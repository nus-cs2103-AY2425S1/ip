package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class UnmarkCommand extends Command {
    private int indexOfTaskToUnmark;

    public UnmarkCommand(int idx) {
        super();
        indexOfTaskToUnmark = idx;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        tasks.unmark(indexOfTaskToUnmark);
        ui.showUnmarkedTask(tasks.getTaskByIndex(indexOfTaskToUnmark));
    }
}
