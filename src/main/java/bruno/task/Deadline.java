package bruno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and includes a date and time by which the task is due.
 */
public class Deadline extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the given description, due date,
     * and completion status.
     *
     * @param description The description of the deadline task.
     * @param by The date and time by which the task is due.
     * @param done The completion status of the task (true if done, false otherwise).
     */
    public Deadline(String description, LocalDateTime by, boolean done) {
        super(description, done);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type,
     * description, completion status, and due date.
     *
     * @return A string that represents the Deadline task.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | by: " + this.by.format(formatter);
    }
}