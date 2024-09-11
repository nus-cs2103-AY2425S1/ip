package vecrosen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task containing 1 additional argument: a due date (not necessarily a date).
 */
public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    private boolean isDate;

    /**
     * Initializes a Deadline object. Starts marked as incomplete.
     *
     * @param description The description of the task
     * @param by The due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        isDate = false;
        try {
            byDate = CustomDateTimeParser.parseDateTime(by);
            isDate = true;
        } catch (DateTimeParseException ignored) {
            // no action
        }
    }

    /**
     * Returns the due date/time/etc of the deadline.
     *
     * @return The due of the deadline
     */
    public String getBy() {
        if (isDate) {
            return byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } else {
            return byString;
        }
    }

    @Override
    public String toString() {
        String taskDesc = super.toString();
        return "[D" + taskDesc.substring(2) + " (by: " + getBy() + ")";
    }
}
