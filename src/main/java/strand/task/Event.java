package strand.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import strand.exception.StrandException;
import strand.exception.StrandIncorrectDateException;

/**
 * The strand.task.Event class represents a task that occurs within a specific time range.
 */
public class Event extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

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
            throw new StrandIncorrectDateException(start);
        }
        try {
            this.endDate = this.parseDate(end);
        } catch (DateTimeParseException e) {
            throw new StrandIncorrectDateException(end);
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
        return String.format("%s%s (from: %s to: %s)", this.getType(), super.toString(),
                this.parseOutputDate(this.startDate),
                this.parseOutputDate(this.endDate));
    }

    @Override
    public String convertToFileFormat() {
        return String.format("E | %s | %s | %s",
                super.convertToFileFormat(),
                this.startDate,
                this.endDate);
    }
}
