package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList tasks, Ui ui) {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printDelete(task, tasks);
    }
}
