package boss.tasks;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, which is a type of task
 * users can add to their list.
 */
public class Event extends TimeTask {
    protected String from;
    protected String to;

    protected LocalDate toDate;
    protected LocalDateTime toTime;

    /**
     * Creates an Event object
     * @param description description of task
     * @param from start date of task
     * @param to end date of task
     * @param isDone status of task
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;

        if (hasDate(from)) {
            from = from.trim();
            this.date = LocalDate.parse(from);
            this.from = date.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        } else if (hasDateTime(from)) {
            from = from.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.time = LocalDateTime.parse(from, formatter);
            this.from = time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "));
        }

        if (hasDate(to)) {
            to = to.trim();
            this.toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        } else if (hasDateTime(to)) {
            to = to.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.toTime = LocalDateTime.parse(to, formatter);
            this.to = toTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "));
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "| from: " + from + "| to: " + to;
    }
}
