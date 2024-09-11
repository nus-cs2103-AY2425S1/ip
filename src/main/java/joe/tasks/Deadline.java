package joe.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import joe.utils.Parser;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime due;

    /**
     * Constructor for Deadline.
     *
     * @param description a String describing the Deadline
     * @param due         a String describing the due date/time
     */
    public Deadline(String description, String due) {
        super(description.strip());
        assert(!description.isEmpty());
        this.due = Parser.parseDateTimeString(due.strip().substring(3));
    }

    /**
     * Returns the number of days until the deadline.
     *
     * @param dateTime the date/time provided by the user
     * @return the number of days until the deadline
     */
    public long daysTillDeadline(LocalDateTime dateTime) {
        return ChronoUnit.DAYS.between(dateTime, due);
    }

    /**
     * Returns a formatted String representing the Deadline object and its fields for saving to file
     *
     * @return a String saved representation of the Deadline object
     */
    @Override
    public String saveRepr() {
        return String.format("D | %s | by %s",
                super.saveRepr(),
                Parser.formatDateTime(due)
        );
    }

    /**
     * Returns a formatted String that represents the Deadline object and its fields
     *
     * @return a String representation of the Deadline object
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), Parser.printDateTime(due));
    }

    @Override
    public LocalDateTime getTime() {
        return due;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            return super.equals(obj) && this.due.equals(other.due);
        }
        return false;
    }
}
