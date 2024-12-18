package barry.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline given by the user.
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in yyyy-mm-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the task in a format to save the file.
     * The format consists of the type of task (ie D for deadline), and the task's completion status,
     * followed by the task description and due date.
     *
     * @return A String representing the deadline task in save format.
     */
    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + by.toString();
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
