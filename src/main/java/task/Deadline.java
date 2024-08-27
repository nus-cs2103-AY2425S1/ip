package task;

import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The task.Deadline class represents a task that has a specific due date.
 * It extends the task.Task class by adding a due date.
 */
public class Deadline extends Task {
    public LocalDateTime date;

    /**
     * Constructs a new task.Deadline with the given description and due date.
     *
     * @param description The description of the task.
     * @param date The due date of the task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return this.date.toLocalDate().equals(date);
    }

    /**
     * Returns a string representation of the deadline, including its status icon, description,
     * and due date.
     *
     * @return A string in the format "[D][statusIcon] description (by: due date)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
