package bobbybot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobbybot.BobbyBotException;

/**
 * Represents an event task.
 * An event task has a description, a start date and an end date.
 */
public class Event extends Task {

    public static final String TASK_TYPE = "E";
    private static final DateTimeFormatter EVENT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Creates a new Event object.
     *
     * @param description Description of the task.
     * @param start       Start date of the event.
     * @param end         End date of the event.
     * @throws BobbyBotException If the start date is after the end date or if the date format is invalid.
     */
    public Event(String description, String start, String end) throws BobbyBotException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new BobbyBotException("Please enter a valid start and/or end date in the format yyyy-mm-dd.");
        }

        if (this.start.isAfter(this.end)) {
            throw new BobbyBotException("Please enter an end date that is after the start date.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s]%s (from: %s to: %s)",
                TASK_TYPE,
                super.toString(),
                start.format(EVENT_DATE_FORMAT),
                end.format(EVENT_DATE_FORMAT)
        );
    }

    @Override
    public String getFileString() {
        return TASK_TYPE + " | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + start + " | " + end;
    }
}
