package task;

import components.Parser;
import exceptions.LightException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws LightException {
        super(description);
        this.by = Parser.dateTimeParser(by, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
    }
    public Deadline(String description, LocalDateTime by) throws LightException {
        super(description);
        this.by = by;
    }
    @Override
    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Deadline(this.description, this.by);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}