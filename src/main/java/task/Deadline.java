package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byInDateFormat;
    protected boolean isInDateFormat = false;

    /**
     * Constructs a new {@code Deadline} with the specified description and deadline date.
     * The deadline date is parsed to determine if it is in a valid date format. If the date is valid,
     * it is stored in a {@code LocalDate} format; otherwise, it is treated as a plain string.
     *
     * @param description the description of the deadline task
     * @param by the deadline date as a string, which is parsed to determine its format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            byInDateFormat = LocalDate.parse(by);
            isInDateFormat = true;
        } catch (DateTimeParseException dtpe) {
            isInDateFormat = false;
        }

    }

    /**
     * Returns a string representation of the task. If the task is in date format, it includes the
     * date in a formatted manner. Otherwise, it returns the raw string representation.
     *
     * @return a formatted string representing the task. The format is:
     *         <ul>
     *             <li>If the task is in date format: "[D] description (by: MMM d yyyy)"</li>
     *             <li>If not in date format: the raw string representation</li>
     *         </ul>
     */
    @Override
    public String toString() {
        if (isInDateFormat) {
            return "[D]" + super.toString() + " (by: "
                    + byInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return this.toRawString();
    }

    /**
     * Returns a raw string representation of the task with deadline details.
     * The format includes the deadline type indicator "[D]", followed by the task's description,
     * and the raw "by" date value.
     *
     * @return a string representing the task in the format:
     *         "[D] description (by: rawByDate)"
     */
    @Override
    public String toRawString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
