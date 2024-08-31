package slave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String task, LocalDate start, LocalDate end) throws InvalidChronologicalOrderException {
        super(task);
        if (start.isAfter(end)) {
            throw new InvalidChronologicalOrderException("Event cannot end before it starts");
        }
        this.start = start;
        this.end = end;
    }

    protected Event(boolean isCompleted, String task, LocalDate start, LocalDate end) throws InvalidChronologicalOrderException {
        super(isCompleted, task);
        if (start.isAfter(end)) {
            throw new InvalidChronologicalOrderException("Event cannot end before it starts");
        }
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawStart() {
        return this.start;
    }

    public String getEnd() {
        return end.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawEnd() {
        return this.end;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }

    @Override
    public String save() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
