package chatbot.impl.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import chatbot.impl.utils.DateTime;

public class Event extends Task {
    private static final String TYPE = "E";
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = DateTime.parseDate(from);
            this.to = DateTime.parseDate(to);

            if (this.from.isAfter(this.to)) {
                throw new IllegalArgumentException("Invalid date range. 'from' date should be before 'to' date");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    public LocalDate getFrom() {
        return from;  // LocalDate is immutable
    }

    /**
     * <p>
     *     Returns a string representation of the event
     *     Used for printing or viewing
     * </p>
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (from: " + DateTime.dmy.format(from) + " to: " + DateTime.dmy.format(to) + ")";
    }

    /**
     * <p>
     *     Returns a serialized representation of the event
     *     Used for data transfer or storage
     * </p>
     * @return a string representation of the event
     */
    @Override
    public String serialize() {
        return TYPE + "|" + super.serialize() + "|" + from + "|" + to;
    }
}
