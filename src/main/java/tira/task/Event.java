package tira.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    private final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");


    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStartDate() {
        return start;
    }

    public LocalDate getEndDate() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(OUT_FORMATTER) + " to: " + end.format(OUT_FORMATTER)+ ")";
    }
}
