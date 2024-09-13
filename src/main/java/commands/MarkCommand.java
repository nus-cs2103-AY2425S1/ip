package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Marks a task index from the list as done.
 * Separation of concerns: Callers must ensure that the task index is within the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Marks a provided task index
     * @param taskIndex the task index to mark
     */
    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Execution actions:
     * - Mark task with selected index as done
     * - Write updated task list to storage
     * - Print updated task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskAt(taskIndex);
        task.markDone();
        storage.writeToTextStorage(tasks);
        return "Nice! I've marked this task as done:\n  " + task;
    }
}
