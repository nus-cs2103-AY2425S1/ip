package nextgpt.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Class for tasks with type event.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            return super.equals(o) &&
                    ((Event) o).start.equals(this.end) && ((Event) o).start.equals(this.end);
        }
        return false;
    }
}
