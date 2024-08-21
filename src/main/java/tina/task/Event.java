package tina.task;

import tina.Parser;
import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    public Event(String des, String start, String end) {
        super(des);
        this.end = Parser.parseDate(end);
        this.start = Parser.parseDate(start);
    }

    public Event(String des, boolean isMark, String start, String end) {
        super(des);
        this.end = LocalDateTime.parse(end);
        this.start = LocalDateTime.parse(start);
        this.isMark = isMark;
    }

    @Override
    public String getDes() {
        return "[E]" + super.getDes() + " (from: " + Parser.formatDate(start) + " to: " + Parser.formatDate(end) + ")";
    }

    @Override
    public String toString() {
        return String.format("E %d %s | %s | %s", isMark? 1 : 0, des, start, end);
    }
}
