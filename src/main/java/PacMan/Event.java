package pacman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    Event(String task, String from, String to) {
        super(task);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public String toFile() {
        return "E/" + super.toFile() + "/" + from + "/" + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
