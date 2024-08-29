package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public static final char TASK_TYPE = 'E';
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate from;
    private final LocalDate to;
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public static Event deserialize(String line) {
        String[] values = line.split("\\|");
        Event event = new Event(
            values[2],
            LocalDate.parse(values[3]),
            LocalDate.parse(values[4])
        );
        if (values[1].equals("X")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.from,
            this.to
        );
    }

    @Override
    public String toString() {
        return String.format(
            "%s  (from: %s to: %s)",
            super.toString(),
            this.from.format(dateFormat),
            this.to.format(dateFormat)
        );
    }
}
