package muller.task;

import java.time.LocalDate;

/**
 * Represents a task that has a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;
    /**
     * Constructs a DeadlineTask object with the specified name and deadline date.
     *
     * @param name     The name of the deadline task.
     * @param deadline The deadline date of the task.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
        this.type = "[D]";
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return The deadline date.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Checks if the task occurs on a specified date.
     *
     * @param date The date to check.
     * @return True if the task's deadline is on the specified date, false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return this.deadline.equals(date);
    }

    /**
     * Checks if the task is due within the next 3 days.
     *
     * @return True if the deadline is within the next 3 days, false otherwise.
     */
    @Override
    public boolean isDueSoon() {
        return deadline.isBefore(LocalDate.now().plusDays(3)) && deadline.isAfter(LocalDate.now());
    }

    /**
     * Converts the DeadlineTask to a string format suitable for saving to a file.
     *
     * @return The string representation of the DeadlineTask for saving.
     */
    @Override
    public String convertToFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getName() + " | " + deadline.format(OUTPUT_DATE_FORMATTER);
    }

    /**
     * Returns a string representation of the task, including its deadline.
     *
     * @return A string representing the task and its deadline.
     */
    @Override
    public String toString() {
        return this.type + getDoneIcon() + " " + getName() + " (by: " + deadline.format(OUTPUT_DATE_FORMATTER) + ")";
    }
}

