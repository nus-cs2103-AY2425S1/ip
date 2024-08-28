package ollie.command;

import ollie.Storage;
import ollie.task.Task;
import ollie.TaskList;
import ollie.Ui;

public class AddCommand extends Command {
    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddTask(task, tasks.getSize());
    }
}
