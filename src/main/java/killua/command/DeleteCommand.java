package killua.command;

import java.io.IOException;

import killua.storage.Storage;
import killua.task.Task;
import killua.ui.Ui;
import killua.util.KilluaException;
import killua.util.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String INVALID_INDEX_MESSAGE = "What are you trying to do? No such task!";
    private final int taskIndex;

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
     * @return A String representation of Killua's response.
     * @throws KilluaException If there is an error related to task handling.
     * @throws IOException If there is an error in reading or writing to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            storage.save(tasks);
            return ui.showTaskDeleted(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KilluaException(INVALID_INDEX_MESSAGE);
        }
    }
}

