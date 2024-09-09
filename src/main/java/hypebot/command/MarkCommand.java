package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class MarkCommand extends Command {
    private int indexOfTaskToMark;

    public MarkCommand(int idx) {
        super();
        indexOfTaskToMark = idx;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        tasks.mark(indexOfTaskToMark);
        ui.showMarkedTask(tasks.getTaskByIndex(indexOfTaskToMark));
    }
}
