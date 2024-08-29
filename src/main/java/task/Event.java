package task;

import java.time.LocalDate;


public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateStringPrintFormat(this.start) + " to: "
                + getDateStringPrintFormat(this.end) + ")";
    }

    @Override
    public String getDatabaseString() {
        return String.format(
                "E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                getDateStringStorageFormat(this.start),
                getDateStringStorageFormat(this.start)
        );
    }
}