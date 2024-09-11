package chatbot.impl.task;

import java.time.LocalDate;

import chatbot.impl.utils.DateTime;

public class Deadline extends Task {
    private static final String TYPE = "D";
    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateTime.parseDate(by);
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
