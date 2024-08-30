package twilight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a deadline to be stored in a task list with a particular description and date.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Instantiates the deadline with it being incomplete.
     *
     * @param description What the task is.
     * @param deadline When the task is due in the formt YYYY-MM-DD.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Instantiates the deadline.
     *
     * @param status Whether the task is complete.
     * @param description What the task is.
     * @param deadline When the task is due in the formt YYYY-MM-DD.
     */
    public Deadline(boolean status, String description, String deadline) {
        super(description, status);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toStorage() {
        return "D," + super.toStorage() + "," + deadline;
    }
}
