package Tick.commands;

import Tick.exceptions.TickException;
import Tick.storage.Storage;
import Tick.tasks.Task;
import Tick.tasks.TaskList;
import Tick.ui.Ui;

public class DeleteCommand extends Command {
    private int toDelete;

    public DeleteCommand(int index) {
        this.toDelete = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        Task deletedTask = tasks.deleteTask(this.toDelete);
        ui.showTaskDeleted(deletedTask, tasks.getSize());
        storage.saveData(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
