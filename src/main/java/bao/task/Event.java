package bao.task;

import bao.main.Bao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | "
                + from.format(Bao.outputDateFormat) + "-" + to.format(Bao.outputDateFormat);
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description.trim()
                + " | " + from.format(Bao.fileDateFormat) + " - " + to.format(Bao.fileDateFormat);
    }
}