package oliver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task that has to be completed by a certain deadline
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the formatted string to be stored in the data file.
     *
     * @return formatted string representing the deadline task
     */
    @Override
    public String getFormatted() {
        return "D|" + super.getStatusIcon() + "|" + super.description + "|" + this.by;
    }

    /**
     * Returns true if the task has a deadline within 2 days of now.
     *
     * @return boolean representing whether the task is an upcoming task
     */
    @Override
    public boolean isUpcoming() {
        LocalDateTime now = LocalDateTime.now();
        long daysDifference = ChronoUnit.DAYS.between(now, this.by);
        return Math.abs(daysDifference) <= 2;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mm a")) + ")";
    }
}
