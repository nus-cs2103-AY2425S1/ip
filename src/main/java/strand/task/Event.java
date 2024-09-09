package strand.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import strand.exception.StrandException;

/**
 * The strand.task.Event class represents a task that occurs within a specific time range.
 */
public class Event extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String stringStartDate;
    protected String stringEndDate;

    /**
     * Constructs a new strand.Tasks.Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(String description, String start, String end) throws StrandException {
        super(description);
        try {
            this.startDate = this.parseDate(start);
        } catch (DateTimeParseException e) {
            this.stringStartDate = start;
        }
        try {
            this.endDate = this.parseDate(end);
        } catch (DateTimeParseException e) {
            this.stringEndDate = end;
        }
    }

    @Override
    String getType() {
        return "[E]";
    }

    /**
     * Returns the string representation of the strand.Tasks.Event task
     *
     * @return A string representing the strand.Tasks.Event task
     */
    @Override
    public String toString() {
        String start = this.startDate != null ? this.parseOutputDate(this.startDate) : this.stringStartDate;
        String end = this.endDate != null ? this.parseOutputDate(this.endDate) : this.stringEndDate;
        return String.format("%s%s from: %s to: %s)", this.getType(), super.toString(), start, end);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("E | %s | %s | %s",
                super.convertToFileFormat(),
                this.startDate != null ? this.startDate : this.stringStartDate,
                this.endDate != null ? this.endDate : this.stringEndDate);
    }
}
