package bobby.command;

import java.time.LocalDate;
import java.util.ArrayList;

import bobby.exceptions.BobbyException;
import bobby.storage.Storage;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;
import bobby.ui.Ui;

/**
 * Represents a command to search for tasks on a specific date.
 * <p>
 * The {@code SearchDateCommand} class encapsulates the logic for finding tasks that are scheduled
 * on a given date. It interacts with the task list to retrieve tasks matching the specified date
 * and communicates the results to the user interface.
 * </p>
 */
public class SearchDateCommand implements Command {
    private final LocalDate date;

    /**
     * Constructs a {@code SearchDateCommand} with the specified date for searching tasks.
     *
     * @param date the {@code LocalDate} representing the date to search for tasks
     */
    public SearchDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the search date command by retrieving tasks scheduled on the specified date.
     * <p>
     * The method searches the task list for tasks that are due on the provided date, updates
     * the storage to ensure the tasks are current, and returns a message containing the list of
     * tasks found on that date through the {@code Ui} instance.
     * </p>
     *
     * @param tasks   the {@code TaskList} containing the tasks to be searched
     * @param ui      the {@code Ui} instance for interacting with the user
     * @param storage the {@code Storage} instance responsible for saving or loading data
     * @return a {@code String} message indicating the tasks found on the specified date
     * @throws BobbyException if an error occurs during command execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ArrayList<Task> foundTasks = tasks.findTasksByDate(this.date);
        storage.saveTasks(tasks);
        return ui.getFoundTasksMessage(foundTasks);
    }
}
