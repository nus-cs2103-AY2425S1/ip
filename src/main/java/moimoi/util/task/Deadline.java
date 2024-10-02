package moimoi.util.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructs a deadline task of the specified description and deadline.
     *
     * @param description Task description.
     * @param deadline Task deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(TaskEnum.D, description);
        this.deadline = deadline;
    }

    /**
     * Checks if the task occurs on the specified date.
     *
     * @param date Date to be checked against the task's deadline.
     * @return true if the task occurs on the specified date; false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return this.deadline.toLocalDate().isEqual(date);
    }

    /**
     * Returns the String representation of the task for UI display.
     *
     * @return String representation of the task for UI display.
     */
    @Override
    public String stringUI() {
        return super.stringUI() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation of the task for storage.
     */
    @Override
    public String stringStorage() {
        return super.stringStorage() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
