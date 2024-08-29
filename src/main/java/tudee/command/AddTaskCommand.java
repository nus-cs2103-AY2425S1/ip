package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.task.Task;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAdd(task, tasks.size());
        storage.save(tasks.get());
    }
}