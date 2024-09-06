package sora.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Deadline is a Task with a deadline.
 */
public class Deadline extends Task {
    /** Deadline of the deadline in String Format */
    protected String by;
    /** Deadline of the deadline in LocalDateTime Format */
    protected LocalDateTime byDate;

    /**
     * Constructs a new Deadline Task.
     * If by is a String, this Constructor is called.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline Task.
     * If byDate is a LocalDateTime, this Constructor is called.
     *
     * @param description The description of the deadline.
     * @param byDate The deadline of the deadline.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String formattedByDate = (this.byDate != null)
                ? this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.by;
        return "[D]" + super.toString() + " (by: " + formattedByDate + ")";
    }

    @Override
    public List<String> getTaskDetails() {
        String formattedByDate = (this.byDate != null)
                ? this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                : this.by;
        return List.of("D", getStatus(), description, formattedByDate);
    }
}
