package Elon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.start + " | " + this.end;
    }
}
