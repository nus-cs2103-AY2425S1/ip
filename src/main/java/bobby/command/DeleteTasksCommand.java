package bobby.command;

import java.util.ArrayList;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;

/**
 * Represents a command to delete multiple tasks from the task list.
 * <p>
 * The {@code DeleteTasksCommand} class encapsulates the logic for removing tasks
 * from the task list based on the provided indices. It interacts with the task list
 * to remove the specified tasks and updates the storage to reflect these changes.
 * </p>
 */
public class DeleteTasksCommand implements Command {

    private String[] tasksIndices;

    /**
     * Constructs a {@code DeleteTasksCommand} with the specified task indices.
     *
     * @param tasksIndices an array of indices indicating which tasks to delete
     */
    public DeleteTasksCommand(String[] tasksIndices) {
        this.tasksIndices = tasksIndices;
    }

    /**
     * Executes the delete command by removing tasks from the task list and updating storage.
     * <p>
     * The method retrieves the tasks based on the provided indices, deletes them from the
     * task list, and then saves the updated task list to the storage. It also returns a message
     * indicating which tasks were deleted and the current size of the task list.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be modified
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message indicating the result of the deletion
     * @throws BobbyException if an error occurs when getting tasks from indices.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ArrayList<Task> tasksToDelete = tasks.getTasksFromIndices(this.tasksIndices);
        tasks.deleteMultipleTasks(tasksToDelete);
        storage.saveTasks(tasks);
        return ui.getTaskDeletedMessage(tasksToDelete, tasks.size());
    }
}
