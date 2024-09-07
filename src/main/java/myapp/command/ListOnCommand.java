package myapp.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import myapp.core.BingBongException;
import myapp.core.DateTimeHandler;
import myapp.core.Storage;
import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * Represents a command that lists all tasks scheduled on a specific date.
 * This command filters tasks by their date and returns only those tasks that match the given date.
 */
public class ListOnCommand extends ListCommand {

    /** The date on which to filter tasks. */
    private LocalDate queryDate;

    /**
     * Constructs a ListOnCommand with the specified date.
     * The date is parsed and validated to ensure it is in the correct format.
     *
     * @param date The date on which to filter tasks.
     * @throws BingBongException If the date format is invalid.
     */
    public ListOnCommand(String date) throws BingBongException {
        super();
        this.queryDate = parseQueryDate(date);
    }

    /**
     * Executes the command by listing all tasks that are scheduled on the specified date.
     * If no tasks are found for the given date, a message indicating this is returned.
     *
     * @param tasks   The task list containing the tasks to be filtered.
     * @param storage The storage system (not used in this command).
     * @return A string message listing the tasks scheduled on the specified date, or a message indicating
     *      no matches were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder list = new StringBuilder("Tasks on " + DateTimeHandler.format(queryDate) + ":\n");
        boolean hasTasks = listMatchingTasks(tasks, list);

        return generateResultMessage(hasTasks, list);
    }

    /**
     * Parses the provided date string into a {@link LocalDate}.
     *
     * @param date The date string provided by the user.
     * @return The parsed {@link LocalDate} object.
     * @throws BingBongException If the date format is invalid.
     */
    private LocalDate parseQueryDate(String date) throws BingBongException {
        try {
            return DateTimeHandler.parseDate(date);
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date format. Please use the format: d/M/yyyy.");
        }
    }

    /**
     * Filters tasks that match the query date and appends them to the provided {@link StringBuilder}.
     *
     * @param tasks The task list containing the tasks.
     * @param list The {@link StringBuilder} to which matching tasks will be appended.
     * @return true if tasks matching the query date are found; false otherwise.
     */
    private boolean listMatchingTasks(TaskList tasks, StringBuilder list) {
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            if (isTaskMatchingDate(task)) {
                appendTaskToList(list, task, count);
                hasTasks = true;
                count++;
            }
        }

        return hasTasks;
    }

    /**
     * Checks whether the task matches the query date.
     *
     * @param task The task to check.
     * @return true if the task matches the query date; false otherwise.
     */
    private boolean isTaskMatchingDate(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDateTime().toLocalDate().equals(queryDate);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getStartDateTime().toLocalDate().equals(queryDate)
                    || event.getEndDateTime().toLocalDate().equals(queryDate);
        }
        return false;
    }

    /**
     * Appends a task to the list with the appropriate numbering.
     *
     * @param list The {@link StringBuilder} to append to.
     * @param task The task to append.
     * @param count The task number in the list.
     */
    private void appendTaskToList(StringBuilder list, Task task, int count) {
        list.append(count).append(". ").append(task).append("\n");
    }

    /**
     * Generates the result message based on whether any tasks were found for the query date.
     *
     * @param hasTasks true if tasks matching the query date were found; false otherwise.
     * @param list The list of tasks found.
     * @return The result message to display to the user.
     */
    private String generateResultMessage(boolean hasTasks, StringBuilder list) {
        if (!hasTasks) {
            return "No tasks found on " + DateTimeHandler.format(queryDate) + ".";
        } else {
            return list.toString();
        }
    }
}
