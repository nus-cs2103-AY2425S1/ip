package dumpling.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class, a special type of task
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Event constructor.
     * @param description Description of event
     * @param from Starting date / date time of event
     * @param to Ending date / date time of event
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        this(description, "", from, to);
    }

    /**
     * General Event constructor.
     * @param description Description of event
     * @param notes event notes
     * @param from Starting date / date time of event
     * @param to Ending date / date time of event
     */
    public Event(String description, String notes, String from, String to) throws DateTimeParseException {
        super(description, notes);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getTaskForSaving() {
        return String.format("E | %d | %s | %s&%s | %s\n", (
                this.isDone ? 1 : 0),
                this.description,
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.notes);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)%s",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.notes.isEmpty() ? "" : String.format(" (%s)", this.notes)
        );
    }
}
