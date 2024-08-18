package commands;

import common.Command;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        Task task = storage.getTask(taskIndex - 1);
        if (task != null) {
            task.markAsUndone();
            ui.printMessage("Okay, I've marked this task as not done yet:\n  " + task);
        } else {
            ui.printMessage("Task not found.");
        }
        return true;
    }
}
