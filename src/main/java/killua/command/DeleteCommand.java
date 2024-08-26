package killua.command;

import killua.task.Task;
import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;
import killua.util.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete the task from the task list.
     * Updates the user interface to show the task has been deleted and saves the updated task list to storage.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The user interface to interact with.
     * @param storage The storage to save the updated task list.
     * @throws KilluaException If there is an error related to task handling.
     * @throws IOException If there is an error in reading or writing to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        Task task = tasks.getTasks().get(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task);
        storage.save(tasks);
    }
}
