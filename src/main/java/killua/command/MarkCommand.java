package killua.command;

import java.io.IOException;

import killua.storage.Storage;
import killua.task.Task;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents a command to mark a specific task as done in the task list.
 * This command updates the status of a specified task to done and saves the changes.
 */
public class MarkCommand extends Command {
    private static final String INVALID_INDEX_MESSAGE = "What are you trying to do? No such task!";
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as done.
     * Updates the status of the task at the specified index to done, shows the updated task to the user,
     * and saves the updated task list to storage.
     *
     * @param tasks The task list containing all tasks.
     * @param ui The user interface to interact with and display the updated task.
     * @param storage The storage to save the updated task list.
     * @return A String representation of Killua's response.
     * @throws KilluaException If there is an error marking the task as done.
     * @throws IOException If there is an error saving the task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            tasks.markTaskDone(taskIndex);
            storage.save(tasks);
            return ui.showTaskMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_INDEX_MESSAGE);
        }
    }
}
