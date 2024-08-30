package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import darkpool.util.DarkpoolException;

public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    public Event(String description, String fromTime, String toTime, boolean isDone) throws DarkpoolException {
        super(description.trim(), isDone);
        try {
            this.fromTime = LocalDateTime.parse(fromTime, Task.formatter);
            this.toTime = LocalDateTime.parse(toTime, Task.formatter);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime.format(Task.formatter) + " to:" + this.toTime.format(Task.formatter) + ")";
    }

    @Override
    public String toFileString() {
        return ("E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.fromTime.format(Task.formatter) + " | " + this.toTime.format(Task.formatter) + "\n");
    }
}
