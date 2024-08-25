package seanbot.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the SeanBot application.
 * A Deadline task has a description and a due date by which it should be completed.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task in the format yyyy-mm-dd.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);  
    }

    /**
     * Converts the Deadline task to a string suitable for saving to a file.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the Deadline task, including its status and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
