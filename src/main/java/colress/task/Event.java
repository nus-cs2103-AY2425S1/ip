package colress.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate DATE;
    private final LocalTime FROM;
    private final LocalTime TO;
    public Event(String description, LocalDate date, LocalTime from, LocalTime to) {
        super(description);
        this.DATE = date;
        this.FROM = from;
        this.TO = to;
    }

    public Event(String description, LocalDate date, LocalTime from, LocalTime to, boolean isDone) {
        super(description, isDone);
        this.DATE = date;
        this.FROM = from;
        this.TO = to;
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.DATE.isEqual(date);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X][E] %s (%s, %s to %s)", getDescription(),
                    this.DATE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.FROM.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.TO.format(DateTimeFormatter.ofPattern("HHmm")));
        } else {
            return String.format("[ ][E] %s (%s, %s to %s)", getDescription(),
                    this.DATE.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                    this.FROM.format(DateTimeFormatter.ofPattern("HHmm")),
                    this.TO.format(DateTimeFormatter.ofPattern("HHmm")));
        }
    }

    @Override
    public String toTextFile() {
        if (getIsDone()) {
            return String.format("[X] | Event | %s | %s | %s to %s", getDescription(), this.DATE, this.FROM, this.TO);
        } else {
            return String.format("[ ] | Event | %s | %s | %s to %s", getDescription(), this.DATE, this.FROM, this.TO);
        }
    }
}
