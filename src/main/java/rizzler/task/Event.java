package rizzler.task;

import rizzler.ui.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDate eventStart;
    private final LocalDate eventEnd;

    public Event(String eventDesc, String eventStart, String eventEnd) {
        this(eventDesc, eventStart, eventEnd, false);
    }

    public Event(String eventDesc, String eventStart, String eventEnd, boolean isDone) throws DateTimeParseException {
        super(eventDesc, isDone);
        this.eventStart = DateTimeParser.to_datetime(eventStart);
        this.eventEnd = DateTimeParser.to_datetime(eventEnd);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.to_str(eventStart)
                + " to: " + DateTimeParser.to_str(eventEnd) + ")";
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + eventStart + "\t" + eventEnd;
    }
}
