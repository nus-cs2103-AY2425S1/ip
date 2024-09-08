package pikappi.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected String by;

    /**
     * Creates a new DeadlineTask object.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        String timeBy = "";
        try {
            LocalDate byDate = LocalDate.parse(by.split(" ")[0]);
            System.out.println(byDate);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by = by;
        }
        if (by.split(" ").length == 1) {
            return;
        }
        try {
            timeBy = by.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime byTime = LocalTime.parse(timeBy, dtf);
            this.by += byTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by += " " + timeBy;
        }
    }

    /**
     * Creates a new DeadlineTask object.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @param isDone The status of the task.
     */
    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
