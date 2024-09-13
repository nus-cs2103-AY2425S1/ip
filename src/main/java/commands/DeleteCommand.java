package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Deletes a task index from the list.
 * Separation of concerns: Callers must ensure that the task index is within the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Initializes a delete command with the task index.
     * @param taskIndex task index which the element to be deleted belongs at
     */
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Execution actions:
     * - Delete task with selected index from the task list
     * - Write updated task list to storage
     * - Print deleted task and inform user on total tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTaskAt(taskIndex);
        storage.writeToTextStorage(tasks);
        return "Got it. I've deleted this task:\n  "
                + task
                + "\nNow you have " + tasks.length() + " tasks in the list.";
    }
}
