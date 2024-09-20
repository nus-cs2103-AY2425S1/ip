package bobby.command;

import java.util.ArrayList;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;


/**
 * Represents a command to unmark specific tasks.
 * <p>
 * The {@code UnmarkTasksCommand} class encapsulates the logic for marking tasks as incomplete.
 * It interacts with the task list to update the status of the specified tasks and communicates
 * the results to the user interface.
 * </p>
 */
public class UnmarkTasksCommand implements Command {
    private String[] tasksIndices;

    /**
     * Constructs an {@code UnmarkTasksCommand} with the specified indices of tasks to unmark.
     *
     * @param tasksIndices an array of {@code String} representing the indices of the tasks to unmark
     */
    public UnmarkTasksCommand(String[] tasksIndices) {
        this.tasksIndices = tasksIndices;
    }

    /**
     * Executes the unmark tasks command by updating the status of the specified tasks to incomplete.
     * <p>
     * The method retrieves tasks based on the provided indices, updates their status to incomplete,
     * saves the updated task list to storage, and returns a message containing the details of the
     * tasks that were unmarked through the {@code Ui} instance.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be operated on
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message indicating the tasks that were unmarked
     * @throws BobbyException if an error occurs during command execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ArrayList<Task> tasksToUnmark = tasks.getTasksFromIndices(tasksIndices);
        tasks.markMultipleTasks(false, tasksToUnmark);
        storage.saveTasks(tasks);
        return ui.getTaskUnmarkedMessage(tasksToUnmark);
    }
}
