package axel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a deadline.
 * Inherits from {@link Task}.
 */
public class DeadlineTask extends Task {
    /** The deadline for the task. */
    protected LocalDateTime by;

    /**
     * Constructs a {@code DeadlineTask} with the specified description and deadline.
     *
     * @param description A description of the task
     * @param by A string representing the deadline in the format "yyyy-MM-dd HHmm"
     */
    public DeadlineTask(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns a string representation of the {@code DeadlineTask}.
     * The format is: "[D][status] (by: MMM d yyyy hh:mm a)".
     * For example, "[D][ ] (by: Sep 3 2024 01:30 PM)".
     *
     * @return A formatted string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a").withLocale(Locale.US);
        String formattedDate = by.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDate.replace("am", "AM").replace("pm", "PM") + ")";
    }

    /**
     * Returns the string representation of this task suitable for saving to a file.
     * The format is: "D | [status] | [description] | [deadline]".
     * For example, "D | 0 | Finish homework | 2024-09-03 1330".
     *
     * @return A string representation of the task in file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}