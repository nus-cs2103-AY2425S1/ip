package drbrown.command;

import drbrown.utils.Storage;
import drbrown.task.Task;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskCreation(task);
        tasks.add(task);
        ui.showCount(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
