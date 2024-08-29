package tudee.command;

import tudee.task.TaskList;
import tudee.ui.Ui;
import tudee.storage.Storage;
import tudee.task.Task;
import tudee.task.Deadline;
import tudee.task.Events;
import tudee.TudeeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to list tasks that are associated with a specific date.
 * This command filters tasks based on the given date and displays tasks that
 * are due on that date or ongoing during that period.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a DateCommand with the specified date string.
     * Parses the date string to a LocalDate object. Throws an exception if the date format is invalid.
     *
     * @param dateStr The date string in yyyy-MM-dd format.
     * @throws TudeeException If the date string cannot be parsed into a LocalDate.
     */
    public DateCommand(String dateStr) throws TudeeException {
        try {
            this.date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new TudeeException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Executes the command to list tasks associated with the specified date.
     * Iterates through the task list and displays tasks that are either deadlines
     * on the specified date or events that include the date within their duration.
     *
     * @param tasks The task list to be checked for tasks on the specified date.
     * @param ui The user interface to update with the list of tasks on the date.
     * @param storage The storage (not used in this command).
     * @throws TudeeException If no tasks are found on the specified date.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException {
        boolean haveTask = false;
        for (Task task : tasks.get()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateTime().isEqual(date)) {
                    System.out.println(deadline);
                    haveTask = true;
                }
            } else if (task instanceof Events) {
                Events events = (Events) task;
                if ((events.getStart().isBefore(date) && events.getEnd().isAfter(date))
                        || events.getStart().isEqual(date) || events.getEnd().isEqual(date)) {
                    System.out.println(events);
                    haveTask = true;
                }
            }
        }
        if (!haveTask) {
            throw new TudeeException("You have no tasks on this date, " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
        }
    }
}
