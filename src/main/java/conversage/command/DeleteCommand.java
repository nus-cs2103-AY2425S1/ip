package conversage.command;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.Task;
import conversage.task.TaskList;
import conversage.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    // conversage.command.DeleteCommand will have a task index to delete
    private int toDel;

    /**
     * Constructs a DeleteCommand with the specified task index to delete.
     *
     * @param toDel the index of the task to be deleted.
     */
    public DeleteCommand(String toDel) {
        this.toDel = Integer.parseInt(toDel);
    }


    /**
     * Executes the delete command, removing a task from the task list, updating the UI, and saving the task list.
     *
     * @param tasks   The task list to delete the task from.
     * @param ui      The UI to update.
     * @param storage The storage to save the task list to.
     * @return A message indicating the task has been deleted.
     * @throws ConverSageException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        assert toDel > 0 && toDel <= tasks.size() : "Task index should be within valid range";
        Task toDelTask = tasks.getTask(this.toDel - 1);
        tasks.deleteTask(toDel - 1);
        ui.showLine();
        ui.showMessage("Noted, I've removed this task:\n  " + toDelTask.toString()
                + "\nYou have " + tasks.size() + " tasks in your list.");
        ui.showLine();
        storage.save(tasks.getTasks());

        return "Noted, I've removed this task:\n  " + toDelTask.toString()
                + "\nYou have " + tasks.size() + " tasks in your list.";
    }
}
