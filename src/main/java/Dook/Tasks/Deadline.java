package Dook.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, that has a description, and a due date.
 */
public class Deadline extends Task{

    /** The due date of the Deadline. */
    protected LocalDateTime by;

    /**
     * Creates a Deadline with the specified description, and when the deadline is due.
     *
     * @param description The description of the Deadline.
     * @param by The due date for the Deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string to be written to a file.
     *
     * @return The formatted string for file storage.
     */
    @Override
    public String fileFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D | " + super.fileFormatted() + " | " + this.by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return "[D]" + super.toString() + " (By: " + this.by.format(formatter) + ")";
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Deadline)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Deadline d = (Deadline) o;

        return d.description.equals(this.description) && d.by.equals(this.by);
    }
}
