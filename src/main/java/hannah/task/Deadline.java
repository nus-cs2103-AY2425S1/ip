package hannah.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A Deadline task has a specified due date and can be marked as completed.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private LocalDate deadline;

    /**
     * Creates a Deadline task with the specified name.
     *
     * @param name The name of the deadline task.
     */
    public Deadline(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format "[D][status] taskName by deadlineDate".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.deadline;
    }

    /**
     * Sets the deadline for the task using the specified date string.
     * The date string must be in the "yyyy-MM-dd" format.
     *
     * @param deadline The deadline date as a string in the "yyyy-MM-dd" format.
     */
    @Override
    public void setDeadline(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use " + DATE_FORMAT + " .");
        }
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task as a LocalDate object.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the task details in a format suitable for saving to a file.
     * The format is "D | status | taskName | by deadline".
     *
     * @return A string representing the task details for saving.
     */
    @Override
    public String saveTaskFormat() {
        String line = "D | " + (isDone() ? "1" : "0") + " | " + getName() + " | by " + this.deadline;
        return line + System.lineSeparator();
    }
}
