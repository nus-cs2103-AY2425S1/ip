package park.task;

import park.parser.DTFormatter;

import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = DTFormatter.format(start);
            this.end = DTFormatter.format(end);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("please input DateTime in format yyyy-MM-dd HHmm", e.getParsedString(), e.getErrorIndex());
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
