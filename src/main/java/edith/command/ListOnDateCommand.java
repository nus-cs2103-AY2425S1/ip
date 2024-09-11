package edith.command;

import edith.Ui;
import edith.Storage;
import edith.EdithException;
import edith.task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to list tasks on a specific date.
 * The ListOnDateCommand class is used to retrieve and display tasks that are due or starting on a specified date.
 */
public class ListOnDateCommand extends Command {
    private String date;

    /**
     * Constructs a ListOnDateCommand with the specified date.
     *
     * @param date A String representing the date for which tasks are to be listed.
     *             The date should be in the format "d/M/yyyy" (e.g., "13/9/2024").
     */
    public ListOnDateCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the ListOnDateCommand by listing tasks that are due or starting on the specified date.
     *
     * <p>This method will:
     * <ul>
     *     <li>Retrieve and display tasks from the TaskList that are due or starting on the specified date.</li>
     *     <li>Handle any DateTimeParseException thrown if the date format is invalid, and wrap it in an EdithException.</li>
     * </ul>
     *
     * @param tasks The TaskList containing all tasks to be checked.
     * @param ui The Ui used to display the tasks due or starting on the specified date.
     * @param storage The Storage used to save changes (not used in this command).
     * @throws EdithException If there is an error in parsing the date or listing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        try {
            return tasks.listTasksOnDate(date, ui);
        } catch (DateTimeParseException e) {
            throw new EdithException(ui.invalidDateTimeError(), 1);
        }
    }
}
