package Alex.Command;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Command to delete a task from the TaskList by index and save the updated list to storage.
 */
public class DeleteCommand extends CommandBase {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        try {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.printTaskDeleted(task);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new AlexException("Task not found.");
        }
    }
}
