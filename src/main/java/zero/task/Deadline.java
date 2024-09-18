package zero.task;

import java.time.LocalDateTime;

/**
 * The {@code Deadline} class represents a task that needs to be completed by a specific date and time.
 * It is a subclass of {@code Task} and adds a {@code LocalDateTime} field to store the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime byDate;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param byDate The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns the status icon of the task, inherited from the {@code Task} class.
     *
     * @return A string representing whether the task is done or not.
     */
    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    /**
     * Snoozes the deadline by extending the due date by one day.
     * This updates the {@code byDate} field to reflect the new deadline.
     */
    public void snooze() {
        this.byDate = this.byDate.plusDays(1);
    }

    /**
     * Converts the {@code Deadline} task to a formatted string for saving to file.
     *
     * @return A string representation of the {@code Deadline} task for file storage.
     */
    @Override
    public String toFormatted() {
        return "D," + this.isDone() + "," + this.description + "," + this.byDate + "\n";
    }

    /**
     * Returns a string representation of the {@code Deadline} task, including its deadline.
     *
     * @return A string in the format [D][status] description (by: day month year).
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.getDayOfMonth() + " " + byDate.getMonth() + " " + byDate.getYear()
                + ")";
    }
}
