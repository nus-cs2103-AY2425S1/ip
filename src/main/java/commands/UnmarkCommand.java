package commands;

import models.Task;

import java.util.List;

public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Context context) {
        List<Task> tasks = context.tasks();
        if (this.taskIndex < 0 || this.taskIndex >= tasks.size()) {
            throw new InvalidIndexException(tasks.size(), this.taskIndex);
        }

        Task task = tasks.get(taskIndex);
        task.unmarkDone();
        System.out.printf("Marked this task as not yet done:\n%s\n", task);
    }
}
