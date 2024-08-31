package park.task;

import park.parser.DtFormatter;

import java.time.format.DateTimeParseException;

/**
 * Represents a Task that has a start and end date and time.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructs an Event object.
     *
     * @param description Description of the task.
     * @param start Start date and time of the task, in format yyyy-MM-dd HHmm.
     * @param end End date and time of the task, in format yyyy-MM-dd HHmm.
     */
    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = DtFormatter.format(start);
            this.end = DtFormatter.format(end);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("please input DateTime in format yyyy-MM-dd HHmm",
                    e.getParsedString(), e.getErrorIndex());
        }
    }

    @Override
    public String encode() {
        return "E/" + this.getStatusIcon() + "/" + description + "/" + start + "/" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: "
                + end +")";
    }
}
