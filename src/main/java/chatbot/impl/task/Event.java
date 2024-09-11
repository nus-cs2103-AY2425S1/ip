package chatbot.impl.task;

import java.time.LocalDate;

import chatbot.impl.utils.DateTime;

public class Event extends Task {
    private static final String TYPE = "E";
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTime.parseDate(from);
        this.to = DateTime.parseDate(to);
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
