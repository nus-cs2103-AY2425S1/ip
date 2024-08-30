package sage.command;

import sage.exception.SageException;
import sage.task.TaskList;
import sage.task.Task;
import sage.ui.Ui;
import sage.storage.Storage;

/**
 * Represents a command to mark the task as not done in the task list
 */
public class UnmarkCommand extends Command {
    private int index;


    /**
     * Constructs a UnmarkCommand object with the specified task index
     *
     * @param index The 1-based index of the task to be marked as not done in the task list
     *              The index is decremented by 1 to match the 0-based indexing of the task list
     */
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
            ui.showMessage("OK, please remember to complete this task later:\n" + task);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
