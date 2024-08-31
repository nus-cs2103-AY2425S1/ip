package myapp.command;

import myapp.core.BingBongException;
import myapp.core.DateTimeHandler;
import myapp.core.Storage;
import myapp.task.Deadline;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        try {
            queryDate = DateTimeHandler.parseDate(date);
        } catch (DateTimeParseException e) {
            throw new BingBongException("Invalid date format. Please use the format: d/M/yyyy.");
        }
    }

    /**
     * Executes the command by listing all tasks that are scheduled on the specified date.
     * If no tasks are found for the given date, a message indicating this is returned.
     *
     * @param tasks   The task list containing the tasks to be filtered.
     * @param storage The storage system (not used in this command).
     * @return A string message listing the tasks scheduled on the specified date, or a message indicating no matches were found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {

        StringBuilder list = new StringBuilder("Tasks on " + DateTimeHandler.format(queryDate) + ":\n");
        boolean hasTasks = false;
        int count = 1;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(queryDate)) {
                    list.append(count).append(". ").append(deadline).append("\n");
                    hasTasks = true;
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(queryDate)
                        || event.getTo().toLocalDate().equals(queryDate)) {
                    list.append(count).append(". ").append(event).append("\n");
                    hasTasks = true;
                    count++;
                }
            }
        }

        if (!hasTasks) {
            return "No tasks found on " + DateTimeHandler.format(queryDate) + ".";
        } else {
            return list.toString();
        }
    }
}
