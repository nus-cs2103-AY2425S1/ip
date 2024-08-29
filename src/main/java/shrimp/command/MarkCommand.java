package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class MarkCommand implements Command {
    private final int index;
    private final Boolean toMark;

    public MarkCommand(int index, Boolean toMark) {
        this.index = index;
        this.toMark = toMark;
    }

    @Override
    public void run(TaskList tasks, Ui ui) {
        Task task;
        if (toMark) {
            task = tasks.getTask(index).markAsDone();
        } else {
            task = tasks.getTask(index).markAsNotDone();
        }
        tasks.replaceTask(index, task);
        ui.printMark(task);
    }
}
