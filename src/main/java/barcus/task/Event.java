package barcus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with from and to dates
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private final static DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructor without isDone
     * @param description of task
     * @param from date
     * @param to date
     * @throws DateTimeParseException when error parsing date
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, fromFormatter);
        this.to = LocalDateTime.parse(to, fromFormatter);
    }

    /**
     * Constructor with isDone
     * @param description of task
     * @param isDone status of task
     * @param from date
     * @param to date
     * @throws DateTimeParseException when error parsing date
     */
    public Event(String description, boolean isDone, String from, String to) throws DateTimeParseException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, fromFormatter);
        this.to = LocalDateTime.parse(to, fromFormatter);
    }

    /**
     * Converts to save file friendly string
     * @return string
     */
    @Override
    public String convertToSavedString() {
        return "E | " + super.convertToSavedString() + " | " + this.from.format(fromFormatter)
                + " | " + this.to.format(fromFormatter);
    }

    /**
     * Return string rep
     * @return string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(toFormatter)
                + " to: " + to.format(toFormatter) + ")";
    }
}
