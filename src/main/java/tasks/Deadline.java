package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * This class extends the Task class and includes a LocalDateTime field for the deadline.
 */

public class Deadline extends Task {

    protected LocalDateTime date;

    /**
     * Constructs a Deadline task with the specified action and deadline.
     *
     * @param action The description of the task.
     * @param date The deadline date and time of the task.
     */

    public Deadline(String action, LocalDateTime date) {
        super(action);
        this.date = date;
    }

    /**
     * Constructs a Deadline task with the specified action, completion status, and deadline.
     *
     * @param action The description of the task.
     * @param complete The completion status of the task.
     * @param date The deadline date and time of the task.
     */

    public Deadline(String action, boolean complete, LocalDateTime date) {
        super(action, complete);
        this.date = date;
    }

    /**
     * Changes the due date of the deadline.
     *
     * @param newDate The new deadline of the task
     */

    @Override
    public void changeDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    /**
     * Returns the date of the deadline task.
     *
     * @return The date of the deadline task as a LocalDate.
     */

    @Override
    public LocalDate getDate() {
        return this.date.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "D | " + super.toString() + " | " + date.format(formatter);
    }
}

