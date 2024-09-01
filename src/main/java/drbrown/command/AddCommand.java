package drbrown.command;

import drbrown.utils.Storage;
import drbrown.task.Task;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskCreation(this.task);
        tasks.add(this.task);
        ui.showCount(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
