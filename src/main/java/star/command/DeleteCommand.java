package star.command;

import star.exception.StarException;
import star.main.Storage;
import star.main.TaskList;
import star.main.Ui;
import star.task.Task;

/**
 * Represents a Command to delete a task from the task list.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {
    private int zeroIndex;

    /**
     * Constructs a deleteCommand using the task's index to be deleted using its index from the printed task list.
     * @param oneIndex index of a task when it is printed as a list, which is index in array + 1.
     */
    public DeleteCommand(int oneIndex) {
        this.zeroIndex = oneIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StarException {
        Task deletedTask = tasks.getTask(zeroIndex);
        tasks.deleteTask(zeroIndex);
        storage.save(tasks.getTasks());
        return ui.deleteSuccessMsg(deletedTask, tasks.length());
    }
}
