package echo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description the description of the deadline task
     * @param deadline the deadline date of the task
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, TaskType.DEADLINE);
        assert deadline != null: "Deadline should not be null";
        this.deadline = deadline;
    }
    /**
     * Returns a formatted string representing the deadline task,
     * including its description and deadline date.
     *
     * @return a string representing the deadline task
     */
    @Override
    public String getTaskString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.getTaskString() + " (by: " + formattedDeadline + ")";
    }
    /**
     * Returns a string representing the deadline task's save format,
     * intended for saving to a file.
     *
     * @return a string representing the deadline task's save format
     */
    @Override
    public String getData() {
        return super.getData() + " | " + this.deadline;
    }
    /**
     * Overrides the parent class's getTempStrings method to retrieve the temporary strings,
     * and formats the deadline date into a "dd-MM-YYYY" format before returning the array.
     *
     * @return An array of temporary strings with the formatted deadline string at index 3.
     */
    @Override
    public String[] getTempStrings() {
        String[] tempStrings = super.getTempStrings();
        tempStrings[3] = this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        return tempStrings;
    }
}