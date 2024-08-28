package astra.task;

import astra.AstraException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate from;
    private final LocalDate to;

    public Event(String name, String from, String to) throws AstraException {
        super(name);
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);
    }

    public Event(boolean done, String name, String from, String to) {
        super(done, name);
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);
    }

    @Override
    public String toText() {
        return String.format("E | %s | %s | %s",
                super.toText(),
                from.format(inputFormatter),
                to.format(inputFormatter));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", from.format(outputFormatter), to.format(outputFormatter));
    }
}
