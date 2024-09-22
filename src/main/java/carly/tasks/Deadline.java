package carly.tasks;

import java.text.MessageFormat;
import java.time.LocalDate;

import carly.exception.CarlyException;

/**
 * Represents a task of type Deadline.
 * A Deadline task includes a description and a due date.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    /** The task type identifier for Deadline tasks.*/
    private static final String TASK_TYPE = "D";

    /** The due date of the task.*/
    private final LocalDate dueDate;

    /** Constructs a new Deadline task with the specified description and due date.*/
    public Deadline(String description, String dueDate) throws CarlyException {
        super(description);
        this.isDone = false;
        try {
            DateTimeParser d = new DateTimeParser(dueDate);
            this.dueDate = d.getLocalDate();
        } catch (Exception e) {
            throw new CarlyException(e.getMessage());
        }
    }

    public String getDueDateAsString() {
        DateTimeParser d = new DateTimeParser(dueDate.toString());
        return d.formatDateTime();
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    @Override
    public int compareTo(Deadline other) {
        return this.dueDate.compareTo(other.getDueDate());
    }

    /**
     * Returns a string representation of the Deadline task, including
     * its type identifier, status, and due date.
     *
     * @return A string formatted as "[D][status] description (by: due date)".
     * @inheritDoc
     */
    @Override
    public String toString() {
        return MessageFormat.format("[{0}]{1} (by: {2})",
                TASK_TYPE,
                super.toString(),
                this.getDueDateAsString());
    }

}
