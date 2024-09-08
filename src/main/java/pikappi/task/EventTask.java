package pikappi.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end time.
 */
public class EventTask extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new EventTask object.
     *
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        try {
            LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
            String time = from.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime fromTime = LocalTime.parse(time, dtf);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.from += fromTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.from = from;
        }
        try {
            LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
            String time = to.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime toTime = LocalTime.parse(time, dtf);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.to += toTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.to = to;
        }
    }

    /**
     * Creates a new EventTask object.
     *
     * @param description The description of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     * @param isDone The status of the task.
     */
    public EventTask(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the task.
     *
     * @return The start time of the task.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the task.
     *
     * @return The end time of the task.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
