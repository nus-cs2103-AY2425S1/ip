package astra.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import astra.AstraException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param name The name of the task.
     * @param by The deadline of the task.
     * @throws AstraException If the deadline is invalid.
     */
    public Deadline(String name, String by) throws AstraException {
        super(name);
        this.by = LocalDate.parse(by, inputFormatter);
    }

    /**
     * Constructor for Deadline.
     *
     * @param done Whether the task is done.
     * @param name The name of the task.
     * @param by The deadline of the task.
     */
    public Deadline(boolean done, String name, String by) throws AstraException {
        super(done, name);
        this.by = LocalDate.parse(by, inputFormatter);
    }

    public LocalDate getDeadline() {
        return by;
    }

    @Override
    public String toTextFile() {
        return "D | " + super.toTextFile() + " | " + by.format(inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by.format(outputFormatter));
    }

    @Override
    public int compareTo(Task other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return by.compareTo(otherDeadline.by);
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return by.compareTo(otherEvent.getStartDate());
        } else if (other instanceof Todo) {
            return -1;
        } else {
            assert false : "Invalid task type.";
            return 0;
        }
    }
}
