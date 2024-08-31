package babblebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and adds a LocalDate field to store the deadline date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Converts the Deadline task to a format suitable for file storage.
     *
     * @return A string representation of the Deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (getStatusIcon().equals("X")) {
            return "D" + " | " + "1" + " | " + this.description + " | " + this.by.format(formatter);
        } else {
            return "D" + " | " + "0" + " | " + this.description + " | " + this.by.format(formatter);
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its deadline date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
