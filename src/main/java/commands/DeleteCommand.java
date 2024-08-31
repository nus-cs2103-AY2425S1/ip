package commands;

import storage.Storage;
import taskList.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Deletes a task index from the list.
 * Separation of concerns: Callers must ensure that the task index is within the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTaskAt(taskIndex);
        storage.writeToTextStorage(tasks);
        ui.printGenericMessage( "Got it. I've deleted this task:\n  " +
                task + "\nNow you have " + tasks.length() + " tasks in the list.");
    }
}
