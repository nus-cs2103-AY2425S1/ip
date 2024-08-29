package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class AddCommand implements Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void run(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.printAdd(task, tasks);
    }
}
