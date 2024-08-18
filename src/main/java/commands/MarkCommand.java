package commands;

import common.Command;
import common.Ui;
import storage.Task;
import storage.TaskStorage;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        Task task = storage.getTask(taskIndex - 1);
        if (task != null) {
            task.markAsDone();
            ui.printMessage("Nice! I've marked this task as done:\n  " + task);
        } else {
            ui.printMessage("Task not found.");
        }
        return true;
    }
}
