package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String action, LocalDateTime start, LocalDateTime end) {
        super(action);
        this.start = start;
        this.end = end;
    }

    public Event(String action, boolean complete, LocalDateTime start, LocalDateTime end) {
        super(action, complete);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDate getDate() {
        return this.start.toLocalDate();
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "E | " + super.toString() + " | " + start.format(dateFormatter)
                + " - " + end.format(dateFormatter);
    }
}
