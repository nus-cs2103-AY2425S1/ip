package tick.commands;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.tasks.Task;
import tick.tasks.TaskList;
import tick.ui.Ui;

public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        tasks.addTask(this.toAdd);
        ui.showTaskAdded(this.toAdd, tasks.getSize());
        storage.saveData(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
