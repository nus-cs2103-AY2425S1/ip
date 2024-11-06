package ipman.commands;

import ipman.models.Task;
import ipman.models.TaskList;
import ipman.ui.Ui;

/**
 * Deletes a <code>Task</code> inside <code>Context</code>'s
 * <code>TaskList</code>
 *
 * @see Task
 * @see Context
 * @see TaskList
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Context context) {
        TaskList tasks = context.tasks();

        if (this.taskIndex < 0 || this.taskIndex >= tasks.size()) {
            throw new InvalidIndexException(tasks.size(), this.taskIndex);
        }

        Task task = tasks.get(this.taskIndex);
        tasks.remove(this.taskIndex);

        Ui ui = context.ui();
        ui.showMessage(String.format("""
                Noted. I've removed this task:
                %s
                Now you have %d tasks in the list.
            """, task, tasks.size()));
    }
}
