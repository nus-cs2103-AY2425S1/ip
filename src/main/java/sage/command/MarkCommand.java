package sage.command;

import sage.task.TaskList;
import sage.task.Task;
import sage.ui.Ui;
import sage.storage.Storage;
import sage.exception.SageException;

/**
 * Represents a command to mark a task as done in the task list
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the specified task index
     *
     * @param index The 1-based index of the task to be marked as done in the task list
     *              The index is decremented by 1 to match the 0-based indexing of the task list
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            ui.showMessage("Great Job! I have marked this task as done:\n" + task);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
