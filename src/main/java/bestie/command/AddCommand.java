package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;
import bestie.Task;
public class AddCommand extends Command {

    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showTaskAdded(task, tasks.size());
    }

}
