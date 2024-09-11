package task;

import components.Parser;
import exceptions.LightException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) throws LightException {
        super(description);
        this.start = Parser.dateTimeParser(start, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
        this.end = Parser.dateTimeParser(end, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
    }
    public Event(String description, LocalDateTime start, LocalDateTime end) throws LightException {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Event(this.description, this.start, this.end);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}