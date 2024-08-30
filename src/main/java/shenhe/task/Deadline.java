package shenhe.task;

import java.time.LocalDateTime;
import shenhe.parser.DateParser;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + DateParser.format(by);
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + DateParser.format(by) + ")";
        }
        return null;
    }
}