package zero.task;

import java.time.LocalDateTime;

/**
 * The {@code Deadline} class represents a task that needs to be completed by a specific date and time.
 * It is a subclass of {@code Task} and adds a {@code LocalDateTime} field to store the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime byDate;

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    @Override
    public String toFormatted() {
        return "D," + this.isDone() + "," + this.description + "," + this.byDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.getDayOfMonth() + " " + byDate.getMonth() + " " + byDate.getYear()
                + ")";
    }
}
