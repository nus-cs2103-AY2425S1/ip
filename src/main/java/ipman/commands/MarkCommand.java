package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;

public class MarkCommand implements Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();
        if (this.taskIndex < 0 || this.taskIndex >= tasks.size()) {
            throw new InvalidIndexException(tasks.size(), this.taskIndex);
        }

        Task task = tasks.get(taskIndex);
        task.markDone();

        context.ui().showMessageF("Marked this task as done:\n%s", task);
    }
}
