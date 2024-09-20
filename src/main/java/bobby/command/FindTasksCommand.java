package bobby.command;

import java.util.ArrayList;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;


/**
 * Represents a command to find tasks in the task list based on a keyword.
 * <p>
 * The {@code FindTasksCommand} class encapsulates the logic for searching tasks
 * within the task list using a specified keyword. It retrieves tasks that match
 * the keyword and returns a message with the search results.
 * </p>
 */
public class FindTasksCommand implements Command {
    private final String keyword;

    /**
     * Constructs a {@code FindTasksCommand} with the specified search keyword.
     *
     * @param keyword the keyword used to search for tasks
     */
    public FindTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that match the keyword.
     * <p>
     * The method searches through the task list for tasks that contain the specified
     * keyword and returns a message with the found tasks. The task list is not modified,
     * but the search results are displayed to the user.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be searched
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message with the found tasks
     * @throws BobbyException if an error occurs during command execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ArrayList<Task> foundTasks = tasks.findTasksByKeyword(this.keyword);
        storage.saveTasks(tasks);
        return ui.getFoundTasksMessage(foundTasks);
    }
}
