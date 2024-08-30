package puke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a deadline task with a description, completion status, and a due date.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a Deadline with the specified description, completion status, and due date.
     *
     * @param description the description of the deadline task
     * @param isDone true if the task is completed, false otherwise
     * @param by the due date of the deadline in the format "dd/MM/yyyy HHmm"
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormatter);
        if (isDone) markAsDone();
    }

    /**
     * Compares this deadline to another object for equality.
     *
     * @param obj the object to compare this deadline to
     * @return true if the object is equal to this deadline, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Deadline deadline = (Deadline) obj;
        return Objects.equals(by, deadline.by);
    }

    /**
     * Returns a string representation of the deadline, including its type indicator and due date.
     *
     * @return a string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the deadline to its file format representation.
     *
     * @return the string representation of the deadline in file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
