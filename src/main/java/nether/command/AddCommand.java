package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.Task;
import nether.task.TaskList;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printTaskAdded(task);
        storage.saveTasks(tasks.getTasks());
    }
}
