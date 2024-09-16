package bobby.command;

import java.util.ArrayList;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;

/**
 * Represents a command to mark multiple tasks as completed.
 * <p>
 * The {@code MarkTasksCommand} class encapsulates the logic for marking one or more tasks
 * as completed based on their indices. It interacts with the task list to update the status
 * of the specified tasks and communicates the changes to the user interface.
 * </p>
 */
public class MarkTasksCommand implements Command {

    /**
     * Constructs a {@code MarkTasksCommand} with the indices of the tasks to be marked.
     *
     * @param tasksToMark an array of indices representing the tasks to be marked as completed
     */
    private String[] tasksIndices;
    public MarkTasksCommand(String[] tasksToMark) {
        this.tasksIndices = tasksToMark;
    }

    /**
     * Executes the mark tasks command by updating the status of the specified tasks to completed.
     * <p>
     * The method retrieves the tasks from the task list using the provided indices, marks them as
     * completed, and updates the storage. A message indicating the successful marking of tasks is
     * then generated and returned through the {@code Ui} instance.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be operated on
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message indicating the tasks that have been marked as completed
     * @throws BobbyException if an error occurs when getting tasks from indices
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ArrayList<Task> tasksToMark = tasks.getTasksFromIndices(this.tasksIndices);
        tasks.markMultipleTasks(true, tasksToMark);
        storage.saveTasks(tasks);
        return ui.getTaskMarkedMessage(tasksToMark);
    }
}
