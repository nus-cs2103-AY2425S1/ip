package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromInDateFormat;
    protected LocalDate toInDateFormat;
    protected boolean isInDateFormat = false;

    /**
     * Constructs a new {@code Event} with the specified description, start date, and end date.
     * Both dates are parsed to determine if they are in a valid date format. If both dates are valid,
     * they are stored in {@code LocalDate} format; otherwise, the dates are treated as plain strings.
     *
     * @param description the description of the event
     * @param from the start date of the event as a string, which is parsed to determine its format
     * @param to the end date of the event as a string, which is parsed to determine its format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        try {
            fromInDateFormat = LocalDate.parse(from);
            toInDateFormat = LocalDate.parse(to);
            isInDateFormat = true;
        } catch (DateTimeParseException dtpe) {
            isInDateFormat = false;
        }
    }

    /**
     * Returns a string representation of the task. If the task has a date format, it includes the
     * start and end dates in a formatted manner. Otherwise, it returns the raw string representation.
     *
     * @return a formatted string representing the task. The format is:
     *         <ul>
     *             <li>If the task is in date format: "[E] description (from: MMM d yyyy to: MMM d yyyy)"</li>
     *             <li>If not in date format: the raw string representation</li>
     *         </ul>
     */
    @Override
    public String toString() {
        if (isInDateFormat) {
            return "[E]" + super.toString() + " (from: "
                    + fromInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                    + toInDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return this.toRawString();
    }

    /**
     * Returns a raw string representation of the task with event details.
     * The format includes the event type indicator "[E]", followed by the task's description,
     * and the raw "from" and "to" date values.
     *
     * @return a string representing the task in the format:
     *         "[E] description (from: rawFromDate to: rawToDate)"
     */
    @Override
    public String toRawString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
    
}
