package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Marks a task index from the list as undone.
 * Separation of concerns: Callers must ensure that the task index is within the task list.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Unmarks a provided task index
     * @param taskIndex the task index to unmark
     */
    public UnmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Execution actions:
     * - Unmark task with selected index as done
     * - Write updated task list to storage
     * - Print updated task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskAt(taskIndex);
        task.markUndone();
        storage.writeToTextStorage(tasks);
        return "Ok, I've marked this task as not done yet:\n  " + task;
    }
}
