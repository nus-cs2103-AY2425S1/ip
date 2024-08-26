package killua.command;

import killua.util.Ui;
import killua.task.Task;
import killua.util.KilluaException;
import killua.util.Storage;
import killua.util.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as not completed.
 * This command updates the status of a specific task to not completed and
 * saves the updated task list to storage.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     * Initializes the taskIndex field with the given index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark the task at the specified index as not completed.
     * Updates the task status, shows the result to the user, and saves the updated task list.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to display the task status.
     * @param storage The storage to save the updated task list.
     * @throws KilluaException If the task index is out of bounds or if there is an error with the task operations.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.unmarkTask(taskIndex);
        Task task = tasks.getTasks().get(taskIndex);
        ui.showTaskUnmarked(task);
        storage.save(tasks);
    }
}