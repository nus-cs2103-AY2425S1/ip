package killua.command;

import java.io.IOException;

import killua.storage.Storage;
import killua.task.Task;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents a command to mark a task as not completed.
 * This command updates the status of a specific task to not completed and
 * saves the updated task list to storage.
 */
public class UnmarkCommand extends Command {
    private static final String INVALID_INDEX_MESSAGE = "What are you trying to do? No such task!";
    private final int taskIndex;

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
     * @return A String representation of Killua's response.
     * @throws KilluaException If the task index is out of bounds or if there is an error with the task operations.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            tasks.unmarkTask(taskIndex);
            storage.save(tasks);
            return ui.showTaskUnmarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_INDEX_MESSAGE);
        }
    }
}
