package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class DeleteCommand extends Command{
    private int indexOfTaskToDelete;

    public DeleteCommand(int idx) {
        super();
        indexOfTaskToDelete = idx;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        Task removedTask = tasks.delete(indexOfTaskToDelete);
        ui.showDeletedTask(removedTask, tasks);
    }
}
