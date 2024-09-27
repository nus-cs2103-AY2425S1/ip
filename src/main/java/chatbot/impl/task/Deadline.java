package chatbot.impl.task;

import java.time.LocalDate;

import chatbot.impl.utils.DateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = DateTime.parseDate(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    public LocalDate getBy() {
        return by;  // LocalDate is immutable
    }

    /**
     * <p>
     *     Returns a string representation of the deadline
     *     Used for printing or viewing
     * </p>
     * @return a string representation of the deadline
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + DateTime.dmy.format(by) + ")";
    }

    /**
     * <p>
     *     Returns a serialized representation of the deadline
     *     Used for data transfer or storage
     * </p>
     * @return a string representation of the deadline
     */
    @Override
    public String serialize() {
        return TYPE + "|" + super.serialize() + "|" + by;
    }
}
