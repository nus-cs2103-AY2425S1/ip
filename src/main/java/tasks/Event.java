package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is an event.
 * Encapsulates the description of a task, with its start and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initialises an Event object.
     *
     * @param description description of the task.
     * @param from start date of the task.
     * @param to end date of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Initialises an Event object.
     *
     * @param description description of the task.
     * @param from start date of the task.
     * @param to end date of the task.
     * @param priority priority of the task.
     */
    public Event(String description, String from, String to, String priority) {
        super(description, priority);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the string of the task to be saved to data file.
     *
     * @return string representing the task information.
     */
    @Override
    public String writeTask() {
        return super.writeTask() + "," + this.from + "," + this.to;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string of task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
