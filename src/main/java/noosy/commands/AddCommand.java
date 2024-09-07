package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.ui.Ui;
import noosy.task.Task;
import noosy.task.TaskList;

public class AddCommand extends Command {

    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        tasks.add(task);
        ui.showTaskAdded(tasks, task);
        storage.addTask(task);
    }
}
