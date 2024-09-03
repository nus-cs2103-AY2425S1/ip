package main.java.impl.task;

import java.time.LocalDate;

import main.java.impl.utils.DateTime;

public class Event extends Task {
    private static final String TYPE = "E";
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTime.parseDate(from);
        this.to = DateTime.parseDate(to);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (from: " + DateTime.dmy.format(from) + " to: " + DateTime.dmy.format(to) + ")";
    }

    @Override
    public String serialize() {
        return TYPE + "|" + super.serialize() + "|" + from + "|" + to;
    }
}
