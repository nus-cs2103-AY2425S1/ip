package megamind.task;

import megamind.exception.InvalidCommandException;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static java.lang.Long.parseLong;

/**
 * The `RecurringTask` class represents a task that recurs at a specified interval.
 * It extends the `Task` class and includes additional information about the start and end times,
 * as well as the recurrence interval.
 */
public class RecurringTask extends Task{
    @Serial
    private static final long serialVersionUID = 1L;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final long interval;
    private boolean hasRecursed = false;

    /**
     * Constructor for the RecurringTask class.
     */
    public RecurringTask(String description, String start, String end, String interval) {
        super(description);
        this.start = LocalDateTime.parse(start.trim(), INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end.trim(), INPUT_FORMATTER);
        this.interval = parseLong(interval);
    }

    /**
     * Factory method for the RecurringTask class.
     *
     * @param description Description of the task.
     * @param start       Start time of the task.
     * @param end         End time of the task.
     * @param interval    Recurrence interval in days.
     * @throws DateTimeParseException If the start or end time is not in the correct format.
     * @throws NumberFormatException If interval is not a valid long.
     * @throws InvalidCommandException If starting, ending or interval is invalid.
     */
    public static RecurringTask create (String description, String start, String end, String interval) throws DateTimeParseException, NumberFormatException, InvalidCommandException {
        LocalDateTime starting = LocalDateTime.parse(start.trim(), INPUT_FORMATTER);
        LocalDateTime ending = LocalDateTime.parse(end.trim(), INPUT_FORMATTER);
        long startingInterval = parseLong(interval);

        if (starting.isAfter(ending)) {
            throw new InvalidCommandException("Starting must be before ending");
        }

        if (starting.isBefore(LocalDateTime.now().minusDays(1))) {
            throw new InvalidCommandException("Starting must be at most 1 day prior");
        }

        if (startingInterval <= 0) {
            throw new InvalidCommandException("Interval must be more than 1 day");
        }

        return new RecurringTask(description, start, end, interval);
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (FROM: " + this.start.format(OUTPUT_FORMATTER)
               + " TO: " + this.end.format(OUTPUT_FORMATTER) + ") " + "Recurs every " + interval + " days";
    }

    /**
     * Handles the recurrence of the task.
     * If the current date and time is after the next occurrence of the task,
     * a new RecurringTask is created with the updated start and end times.
     *
     * @return A new RecurringTask with updated start and end times, or null if the task does not need to recur yet.
     */
    public RecurringTask handleRecurringTask() {
        if (LocalDateTime.now().isAfter(start.plusDays(interval)) && !hasRecursed) {
            hasRecursed = true;
            String newStart = start.plusDays(interval).format(INPUT_FORMATTER);
            String newEnd = end.plusDays(interval).format(INPUT_FORMATTER);
            String interval = Long.toString(this.interval);
            return new RecurringTask(getDescription(), newStart, newEnd, interval);
        } else {
            return null;
        }
    }
}
