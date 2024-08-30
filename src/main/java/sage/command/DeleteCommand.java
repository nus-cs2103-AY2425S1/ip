package sage.command;

import sage.exception.SageException;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;

/**
 * Represents a command to delete a task from the list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified task index
     *
     * @param index The 1-based index of the task to be deleted in the task list
     *              The index is decremented by 1 to match the 0-based indexing of the TaskList
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.removeTask(index);
            ui.showMessage("OK! I will remove this task:\n" + task +
                    "\nNow you have " + tasks.size() +
                    (tasks.size() > 1 ? " tasks" : " task") + " in your list");
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
