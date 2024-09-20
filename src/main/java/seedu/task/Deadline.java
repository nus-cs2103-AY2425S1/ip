package seedu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline, which includes a description and an end date.
 */
public class Deadline extends Task {
    private LocalDate end;

    /**
     * Constructs a {@code Deadline} task with the specified description and end date.
     *
     * @param description The description of the deadline task.
     * @param end The end date of the deadline task in the format YYYY-MM-DD.
     */
    public Deadline(String description, String end) {
        super(description);
        this.end = LocalDate.parse(end);
    }

    /**
     * Converts the {@code Deadline} object into a string representation for display purposes.
     * The format includes the task type, status, description, and the end date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        String ending = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + ending + ")";
    }

    /**
     * Converts the {@code Deadline} object into a string format suitable for saving in a text file.
     * The format includes task type, completion status, description, and end date.
     *
     * @return A formatted string representing the task to be saved.
     */
    @Override
    public String toSave() {
        return "D" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.end;
    }

    /**
     * Compares this {@code Deadline} with the specified object for equality.
     * Two {@code Deadline} objects are considered equal if they have the same description and end date.
     *
     * @param obj The object to compare this {@code Deadline} with.
     * @return {@code true} if the specified object is a {@code Deadline} with the same description and end date;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline t = (Deadline) obj;
            if (this.description == null || t.description == null) {
                return false;
            }
            if (this.end == null || t.end == null) {
                return false;
            }

            return this.description.equals(t.description) && this.end.equals(t.end);
        }

        return false;
    }
}
