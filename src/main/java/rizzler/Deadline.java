package rizzler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a date to meet by.
 * Inherits from <code>Task</code>.
 */
class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Constructs a new <code>Deadline</code>.
     *
     * @param name Name of the deadline.
     * @param dueDate Due date of the deadline.
     */
    Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new <code>Deadline</code>,
     * but specifying if the deadline is completed.
     * This is explicitly for <code>FileStorage</code> to load
     * tasks saved in the file.
     *
     * @param name Name of the deadline.
     * @param dueDate Due date of the deadline.
     * @param isDone If the deadline is completed or not.
     */
    Deadline(String name, LocalDate dueDate, boolean isDone) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Compares deadlines by due date first, then name.
     *
     * @param other the object to be compared.
     * @return < 0 is less, 0 if equals, > 0 if more.
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Deadline deadline) {
            if (this.dueDate.compareTo(deadline.dueDate) == 0) {
                return super.compareTo(other);
            } else {
                return this.dueDate.compareTo(deadline.dueDate);
            }
        } else {
            return super.compareTo(other);
        }
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return String that represents the deadline.
     */
    @Override
    public String toString() {
        return "[D][" + (this.isDone ? "X" : " ") + "] "
                + this.name
                + " (by: "
                + this.dueDate.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a String representation of the deadline for
     * <code>FileStorage</code> to save to the file.
     *
     * @return String that represents the deadline in the save file.
     */
    @Override
    String toSaveState() {
        return "D" + "/" + (this.isDone ? "1" : "0") + "/"
                + this.name + "/"
                + this.dueDate.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "\n";
    }
}
