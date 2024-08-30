package colress.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;
    public Event(String description, LocalDate date, LocalTime from, LocalTime to) {
        super(description);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate date, LocalTime from, LocalTime to, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.date.isEqual(date);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][E] %s (%s, %s to %s)", getDescription(),
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.from.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.to.format(DateTimeFormatter.ofPattern("HHmm")));
        } else {
            return String.format("[ ][E] %s (%s, %s to %s)", getDescription(),
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.from.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.to.format(DateTimeFormatter.ofPattern("HHmm")));
        }
    }

    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | Event | %s | %s | %s to %s", getDescription(), this.date, this.from, this.to);
        } else {
            return String.format("[ ] | Event | %s | %s | %s to %s", getDescription(), this.date, this.from, this.to);
        }
    }
}
