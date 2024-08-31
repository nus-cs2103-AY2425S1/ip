package tars.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 *
 * <p>The {@code Deadline} class extends the {@link Task} class to include a deadline date.
 * It provides functionality to format the task details for saving and display purposes.</p>
 */
public class Deadline extends Task {

    LocalDate deadlineDate;

    /**
     * Constructs a {@code Deadline} task with the specified name and deadline date.
     *
     * @param name         The name of the task.
     * @param deadlineDate The {@link LocalDate} by which the task must be completed.
     */
    public Deadline(String name, LocalDate deadlineDate) {

        super(name);
        this.deadlineDate = deadlineDate;
    }


    /**
     * Prepares the deadline task information for saving to a file.
     *
     * @return A {@link String} representing the serialized task information in the format:
     *         "D|status|taskName|deadlineDate".
     */
    @Override
    public String saveTask() {

        return String.format("D|%s|%s|%s", super.getStatus(), super.getTask(),
                deadlineDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }

    /**
     * Returns a string representation of the {@link Deadline} task, including its completion status
     * and deadline date.
     *
     * @return A formatted string representing the task's status, name, and deadline date.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
