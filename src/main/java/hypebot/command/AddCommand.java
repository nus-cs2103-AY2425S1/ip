package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

public class AddCommand extends Command{
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        super();
        this.taskToAdd = taskToAdd;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        tasks.add(taskToAdd);
        ui.showAddedTask(taskToAdd, tasks);
    }
}
