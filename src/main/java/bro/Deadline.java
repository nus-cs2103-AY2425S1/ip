package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task for saving/loading purposes.
     * The string includes the task type, status, description, and deadline in a specific format.
     *
     * @return A string representing the Deadline task, formatted for storage.
     *         Format: [D][status] description /by yyyy-MM-dd HHmm
     */
    public String toLoad() {
        return "[D]" + super.toString() + " /by "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Deadline task for display purposes.
     * The string includes the task type, status, description, and a human-readable deadline.
     *
     * @return A string representing the Deadline task, formatted for display.
     *         Format: [D][status] description (by: dd LLL yyyy HHmm)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd LLL yyyy HHmm")) + ")";
    }
}
