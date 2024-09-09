package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;
import strand.exception.StrandException;
import strand.task.Task;

/**
 * The {@code DeleteCommand} class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final Integer index;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index to be deleted.
     *
     * @param index The index of the task to delete in the task list.
     */
    public DeleteCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the delete command, which removes a task from the task list,
     * updates the user interface, and saves the updated task list to storage.
     *
     * @param tasks   The current list of tasks from which a task is to be deleted.
     * @param ui      The {@code Ui} object used to interact with the user.
     * @param storage The {@code Storage} object responsible for saving the updated task list.
     * @throws StrandException If an error occurs while deleting the task or saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        Task deleted = tasks.deleteTask(this.index);
        String output = ui.taskRemoved(deleted, tasks.getNumOfTasks());
        storage.save(tasks.toFile());
        return output;
    }
}
