package bangmang.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 * Inherits from the Task class and is used for tasks that have a specific due date and time.
 */

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with a description and due date.
     *
     * @param description Description of the deadline.
     * @param by Due date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline with a description, completion status, and due date.
     *
     * @param description Description of the deadline.
     * @param isDone Whether the deadline is marked as done.
     * @param by Due date and time of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline.
     * Formats the due date and time based on the current date.
     *
     * @return A string with the format specific to Deadline tasks, including the task type and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter sameYearFullFormatter = DateTimeFormatter.ofPattern("d MMM HH:mm");
        DateTimeFormatter diffYearFullFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Format the due date based on different conditions
        String byString = this.by.format(sameYearFullFormatter);

        // Different year
        if (LocalDateTime.now().getYear() != this.by.getYear()) {
            byString = this.by.format(diffYearFullFormatter);

        } else if (LocalDateTime.now().toLocalDate().equals(this.by.toLocalDate())) {
            // Within the same day and same year
            byString = "today " + this.by.format(timeFormatter);
        }

        return "[D]" + super.toString() + " | " + byString;
    }
}
