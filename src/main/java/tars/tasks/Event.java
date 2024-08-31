package tars.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific start and end date.
 *
 * <p>The {@code Event} class extends the {@link Task} class to include a start date
 * and an end date. It provides functionality to format the task details for saving
 * and display purposes.</p>
 */
public class Event extends Task {

    LocalDate startDate;
    LocalDate endDate;

    /**
     * Constructs an {@code Event} task with the specified name, start date, and end date.
     *
     * @param name      The name of the task.
     * @param startDate The {@link LocalDate} representing the start date of the event.
     * @param endDate   The {@link LocalDate} representing the end date of the event.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Prepares the event task information for saving to a file.
     *
     * @return A {@link String} representing the serialized task information in the format:
     *         "E|status|taskName|startDate|endDate".
     */
    @Override
    public String saveTask() {

        return String.format("E|%s|%s|%s|%s", super.getStatus(), super.getTask(),
                startDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")),
                endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    /**
     * Returns a string representation of the event task, including its completion status,
     * start date, and end date.
     *
     * @return A formatted string representing the task's status, name, start date, and end date.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
