package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a deadline task with the specified description and due date.
     * By default, the task is uncompleted.
     *
     * @param description The description of the task.
     * @param by The date by which the task should be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the task into a string for storage, including the task type ("D" for deadline),
     * its completion status, description, and due date.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        String by = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("D | %s | %s", super.formatString(), by);
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked), its description and due date.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String by = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }

    /**
     * Compares this Task to another object for equality. Two tasks are considered
     * equal if they have the same description, completion status and due date.
     *
     * @param o The object to compare this Task with.
     * @return true if the other object is a Task with the same description, status and due date, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) o;
        return this.by.equals(other.by) && super.equals(other);
    }
}
