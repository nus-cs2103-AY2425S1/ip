package socchat.task.deadline;

import socchat.Parser;
import socchat.task.Task;

import java.time.LocalDateTime;


public class Deadline extends Task {
    private Parser parser;
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        parser = new Parser();
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        parser = new Parser();
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parser.dateToString(by) + ")";
    }

    public String toSave() {
        return "D" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + parser.dateToString(by);
    }
}
