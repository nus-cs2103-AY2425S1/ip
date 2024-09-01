package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Event extends Task {

    protected LocalDate fromDuration;
    protected LocalDate toDuration;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, LocalDate fromDuration, LocalDate toDuration) {
        super(description);
        this.toDuration = toDuration;
        this.fromDuration = fromDuration;
        Task.taskCount++;
    }

    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + fromDuration + " | " + toDuration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDuration.format(outputFormatter) + " to: " + this.toDuration.format(outputFormatter) + ")";
    }

    public LocalDate getFromDur() {
        return this.fromDuration;
    }

    public LocalDate getToDur() {
        return this.toDuration;
    }
}
