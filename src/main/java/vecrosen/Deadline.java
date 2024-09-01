package vecrosen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    private boolean isDate;

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        isDate = false;
        try {
            byDate = CustomDateTimeParser.parseDateTime(by);
            isDate = true;
        } catch (DateTimeParseException ignored) {
        }
    }

    /**
     * Returns the due date/time/etc of the deadline.
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
        String prev = super.toString();
        return "[D" + prev.substring(2) + " (by: " + getBy() + ")";
    }
}
