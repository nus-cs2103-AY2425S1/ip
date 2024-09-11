package nether.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nether.NetherException;


/**
 * Represents a task with a deadline, which includes a description and a specific date/time by when it should be
 * completed.
 * The {@code DeadlineTask} class is a subclass of the {@link Task} class and adds a deadline component to the task.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a {@code DeadlineTask} object with the specified description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and time in the format {@code yyyy-MM-dd HHmm}.
     * @throws NetherException If the date/time format is invalid.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        assert by.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}") : "Date format must be YYYY-MM-DD HHmm";

        // Validate the input date/time and then assign it
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeException e) {
            throw new NetherException("the date/time format for the deadline is invalid. Please use "
                    + "the format: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Returns the string representation of the {@code DeadlineTask} in the format desired for saving into a data file.
     * The format is: {@code D|status|description|yyyy-MM-dd HHmm}, where {@code D} indicates a deadline task.
     *
     * @return A string in the format {@code D|status|description|deadline}.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D|" + getStatusIcon() + "|" + this.getDescription() + "|" + this.by.format(formatter);
    }

    /**
     * Returns the string representation of the {@code DeadlineTask}.
     * The format is: {@code [D][status] description (by: MMM dd yyyy, h:mma)}, where {@code [D]} indicates a
     * deadline task.
     *
     * @return A string representation of the {@code DeadlineTask}.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + "(by: " + this.by.format(formatter) + ")";
    }
}
