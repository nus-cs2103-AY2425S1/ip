package star.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private String by;
    private LocalDateTime date;

    /**
     * Constructs a Deadline task with the given description and deadline date details (not in date format).
     * @param description String that gives the description of the Deadline task.
     * @param by String that gives the date details of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description and deadline date (in date format).
     * @param description String that gives the description of the Deadline task.
     * @param date LocalDateTime that gives the deadline date of the Deadline task.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs a Deadline task with the given description, tag and deadline date details (not in date format).
     * @param description String that gives the description of the Deadline task.
     * @param tag String that gives the tag applied to the Deadline task.
     * @param by String that gives the date details of the Deadline task.
     */
    public Deadline(String description, String tag, String by) {
        super(description, tag);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description, tag and deadline date (in date format).
     * @param description String that gives the description of the Deadline task.
     * @param tag String that gives the tag applied to the Deadline task.
     * @param date LocalDateTime that gives the deadline date of the Deadline task.
     */
    public Deadline(String description, String tag, LocalDateTime date) {
        super(description, tag);
        this.date = date;
    }

    @Override
    public String getOutput() {
        if (by == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            return hasTag() ? String.format("D | %d | %s | #%s | %s", isDone ? 1 : 0, description, super.tag,
                    date.format(formatter))
                    : String.format("D | %d | %s | %s", isDone ? 1 : 0, description, date.format(formatter));
        }
        return hasTag() ? String.format("D | %d | %s | #%s | %s", isDone ? 1 : 0, description, super.tag, by)
                : String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        if (by == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            return hasTag() ? String.format("[D] %s (by: %s) #%s", super.toString(), date.format(formatter), super.tag)
                    : String.format("[D] %s (by: %s)", super.toString(), date.format(formatter));
        }
        return hasTag() ? String.format("[D] %s (by: %s) #%s", super.toString(), by, super.tag)
                : String.format("[D] %s (by: %s)", super.toString(), by);
    }
}
