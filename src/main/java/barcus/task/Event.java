package barcus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private final static DateTimeFormatter fromFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static DateTimeFormatter toFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDateTime.parse(from, fromFormatter);
        this.to = LocalDateTime.parse(to, fromFormatter);
    }

    public Event(String description, boolean isDone, String from, String to) throws DateTimeParseException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, fromFormatter);
        this.to = LocalDateTime.parse(to, fromFormatter);
    }

    @Override
    public String convertToSavedString() {
        return "E | " + super.convertToSavedString() + " | " + this.from.format(fromFormatter)
                + " | " + this.to.format(fromFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(toFormatter)
                + " to: " + to.format(toFormatter) + ")";
    }
}
