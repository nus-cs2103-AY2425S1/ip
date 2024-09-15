package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import darkpool.DarkpoolException;

public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    public Event(String description, String fromTime, String toTime, boolean isDone) throws DarkpoolException {
        super(description.trim(), isDone);

        try {
            this.fromTime = LocalDateTime.parse(fromTime, Task.FORMATTER);
            this.toTime = LocalDateTime.parse(toTime, Task.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("do i really need to tell you how to input the date and time?");
        }
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime.format(Task.FORMATTER) + " to:" + this.toTime.format(Task.FORMATTER) + ")";
    }


    @Override
    public String toFileString() {
        return ("E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.fromTime.format(Task.FORMATTER) + " | " + this.toTime.format(Task.FORMATTER) + "\n");
    }
}
