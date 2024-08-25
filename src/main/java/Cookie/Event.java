package Cookie;

import java.time.LocalDate;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDate fromDeadline;
    private LocalDate toDeadline;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromDeadline = DateParser.convertStringToDate(this.from);
        toDeadline = DateParser.convertStringToDate(this.to);
    }

    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
        fromDeadline = DateParser.convertStringToDate(this.from);
        toDeadline = DateParser.convertStringToDate(this.to);
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + (fromDeadline != null ? DateParser.changePattern(fromDeadline) : this.from)
                + " to: " + (toDeadline != null ? DateParser.changePattern(toDeadline) : this.to) + " )";
    }
}
