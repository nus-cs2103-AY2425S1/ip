package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;

/**
 * Marks a <code>Task</code> inside <code>Context</code>'s
 * <code>TaskList</code> as complete
 * @see Task
 * @see Context
 * @see TaskList
 */
public class MarkCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a command that marks the task at the index as complete
     * @param taskIndex index of task to mark as complete
     */
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
