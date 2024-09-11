package duke.commands;

import java.time.LocalDateTime;
import java.util.List;

import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to filter tasks that are occurring on a specified date and time in the DailyTasks application.
 * This command retrieves and displays a list of tasks that occur at the specified datetime.
 */
public class FilterTaskCommand extends Command {

    /** The datetime used to filter the tasks that occur on this date and time. */
    private final LocalDateTime dateTime;

    /**
     * Constructs a new FilterTaskCommand with the specified datetime.
     *
     * @param dateTime The datetime to filter tasks by.
     */
    public FilterTaskCommand(LocalDateTime dateTime) {
        super();
        // Assert that the provided dateTime is not null
        assert dateTime != null : "DateTime for filtering tasks must not be null";
        this.dateTime = dateTime;
    }

    /**
     * Executes the command by filtering tasks that occur at the specified datetime.
     * The filtered tasks are then displayed using the user interface.
     *
     * @param taskList The list of tasks to be filtered.
     * @param ui The user interface used to display the filtered list of tasks.
     * @param storage The storage system responsible for saving and loading tasks (not used in this implementation).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        // Assert that taskList and ui are not null before proceeding
        assert taskList != null : "Task list must not be null";
        assert ui != null : "UI object must not be null";

        // Fetch the tasks that occur at the specified dateTime
        List<Task> tasks = taskList.getTasksOccurring(dateTime);

        // Assert that the task list returned is not null (it may be empty, but shouldn't be null)
        assert tasks != null : "Task list returned by getTasksOccurring must not be null";

        return ui.formatTaskListings(tasks, true);
    }
}
